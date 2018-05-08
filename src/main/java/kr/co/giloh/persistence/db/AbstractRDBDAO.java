package kr.co.giloh.persistence.db;

import javafx.collections.ObservableList;
import kr.co.giloh.model.bean.database.DBAuthenticationCredential;

import java.sql.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractRDBDAO<T> implements RDBDataAccessObject<T> {
	private DBAuthenticationCredential dbAuth;
	private Connection conn;
	private Statement stmt;

	private int timeoutSecs = 30;

	@Override
	public void setupDBAuth(String protocol, String host, int port, String databaseName, String user, String password) {
		this.dbAuth = new DBAuthenticationCredential(protocol, host, port, databaseName, user, password);
	}

	public void setTimeoutSecs(int timeoutSecs) {
		this.timeoutSecs = timeoutSecs;
	}


	@Override
	public void connect() throws SQLException {
		String url = String.format("jdbc:%s://%s:%s/%s", dbAuth.getProtocol(), dbAuth.getHost(), String.valueOf(dbAuth.getPort()), dbAuth.getDatabaseName());
		conn = DriverManager.getConnection(url, dbAuth.getUser(), dbAuth.getPassword());
		stmt = conn.createStatement();
	}

	@Override
	public void disconnect() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}


	@Override
	public boolean create(T itemToAdd) {
		try {
			if (!conn.isValid(timeoutSecs)) {
				connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		boolean result = false;


		try {
			int cnt = stmt.executeUpdate(writeInsertSQL(itemToAdd));
			result = cnt > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ObservableList<T> read() {
		try {
			if (!conn.isValid(timeoutSecs)) {
				connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		getSrcList().clear();


		try {
			stmt.execute(writeSelectSQL());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (ResultSet rs = stmt.getResultSet();) {
			while (rs.next()) {
				getSrcList().add(retrieveFrom(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// transfer src to filter
		getFilteredList().clear();
		getFilteredList().addAll(getSrcList());


		return getFilteredList();
	}

	@Override
	public boolean update(T oldItem, T newItem) {
		try {
			if (!conn.isValid(timeoutSecs)) {
				connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		boolean result = false;


		try {
			int cnt = stmt.executeUpdate(writeUpdateSQL(oldItem, newItem));
			if (cnt > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean delete(T itemToDel) {
		try {
			if (!conn.isValid(timeoutSecs)) {
				connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		boolean result = false;
		try {
			int cnt = stmt.executeUpdate(writeDeleteSQL(itemToDel));

			if (cnt > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}

		return result;
	}

	public ObservableList<T> search(Predicate<T> filterFunc) {

		getFilteredList().clear();

		getFilteredList().addAll(getSrcList().stream().filter(filterFunc).collect(Collectors.toList()));


		return getFilteredList();

	}

	public void restore() {
		getFilteredList().clear();
		getFilteredList().addAll(getSrcList());
	}

	public Statement getStmt(){
		return stmt;
	}

	public abstract String writeInsertSQL(T itemToAdd);

	public abstract String writeUpdateSQL(T oldItem, T newItem);

	public abstract String writeDeleteSQL(T itemToDel);

	public abstract String writeSelectSQL();

	public abstract T retrieveFrom(ResultSet rs);

	public abstract ObservableList<T> getSrcList();

	public abstract ObservableList<T> getFilteredList();
}
