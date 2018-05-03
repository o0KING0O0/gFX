package kr.co.giloh.persistence.db;

import kr.co.giloh.persistence.DataAccessObject;

import java.sql.SQLException;

public interface RDBDataAccessObject<T> extends DataAccessObject<T> {
	void setupDBAuth(String protocol, String host, int port, String databaseName, String user, String password);

	void connect() throws SQLException;

	void disconnect() throws SQLException;
}

