package kr.co.giloh.model.name;

import javafx.beans.property.StringProperty;

public interface MutableNameProperty {
	StringProperty nameProperty();

	String getName();

	void setName(String name);
}
