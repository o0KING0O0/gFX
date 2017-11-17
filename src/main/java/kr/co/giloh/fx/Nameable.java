package kr.co.giloh.fx;

import javafx.beans.property.StringProperty;

public interface Nameable {
	public StringProperty nameProperty();

	public String getName();

	public void setName(String name);
}
