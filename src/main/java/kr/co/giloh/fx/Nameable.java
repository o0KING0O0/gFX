package kr.co.giloh.fx;

import javafx.beans.property.StringProperty;

public interface Nameable {
	StringProperty nameProperty();

	String getName();

	void setName(String name);
}
