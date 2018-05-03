package kr.co.giloh.model.bean.database;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DBAuthenticationCredential {
	private StringProperty protocol;
	private StringProperty host;
	private IntegerProperty port;
	private StringProperty databaseName;
	private StringProperty user;
	private StringProperty password;


	public DBAuthenticationCredential(String protocol, String host, int port, String databaseName, String user, String password) {
		this.protocol = new SimpleStringProperty(protocol);
		this.host = new SimpleStringProperty(host);
		this.port = new SimpleIntegerProperty(port);
		this.databaseName = new SimpleStringProperty(databaseName);
		this.user = new SimpleStringProperty(user);
		this.password = new SimpleStringProperty(password);
	}


	public String getProtocol() {
		return protocol.get();
	}

	public void setProtocol(String protocol) {
		this.protocol.set(protocol);
	}

	public StringProperty protocolProperty() {
		return protocol;
	}

	public String getHost() {
		return host.get();
	}

	public void setHost(String host) {
		this.host.set(host);
	}

	public StringProperty hostProperty() {
		return host;
	}

	public int getPort() {
		return port.get();
	}

	public void setPort(int port) {
		this.port.set(port);
	}

	public IntegerProperty portProperty() {
		return port;
	}

	public String getDatabaseName() {
		return databaseName.get();
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName.set(databaseName);
	}

	public StringProperty databaseNameProperty() {
		return databaseName;
	}

	public String getUser() {
		return user.get();
	}

	public void setUser(String user) {
		this.user.set(user);
	}

	public StringProperty userProperty() {
		return user;
	}

	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public StringProperty passwordProperty() {
		return password;
	}
}
