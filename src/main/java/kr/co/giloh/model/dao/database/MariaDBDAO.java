package kr.co.giloh.model.dao.database;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import kr.co.giloh.persistence.DataTransferObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MariaDBDAO<T extends DataTransferObject<T>> implements SimpleDBDAO<T> {
	private StringProperty host;
	private IntegerProperty port;
	private StringProperty database;
	private StringProperty user;
	private StringProperty password;

	private Connection conn;
	private Statement stmt;

	public MariaDBDAO() {

	}

	public MariaDBDAO(String host, int port, String database, String user, String password) {
		this.host = new SimpleStringProperty(host);
		this.port = new SimpleIntegerProperty(port);
		this.database = new SimpleStringProperty(database);
		this.user = new SimpleStringProperty(user);
		this.password = new SimpleStringProperty(password);
	}

	public void connect() throws SQLException {
		String url = String.format("jdbc:mysql://%s:%s/%s", host.get(), String.valueOf(port), database.get());
		conn = DriverManager.getConnection(url, user.get(), password.get());
		if (conn.isValid(600)) {
			stmt = conn.createStatement();
		}
	}

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
			getDBColumnMap(itemToAdd);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean put(T itemToAdd) {
		return false;
	}

	@Override
	public ObservableList<T> read() {
		return null;
	}

	@Override
	public T read(int index) {
		return null;
	}

	@Override
	public boolean update(int index, T newItem) {
		return false;
	}

	@Override
	public boolean set(int index, T newItem) {
		return false;
	}

	@Override
	public boolean delete(int index) {
		return false;
	}

	@Override
	public boolean deleteAll() {
		return false;
	}

	@Override
	public boolean moveUp(int index) {
		return false;
	}

	@Override
	public boolean moveDown(int index) {
		return false;
	}

	@Override
	public void copy(ObservableList<T> objList) {

	}

	@Override
	public T find(T obj) {
		return null;
	}
}
