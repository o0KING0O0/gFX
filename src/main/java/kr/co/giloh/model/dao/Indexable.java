package kr.co.giloh.model.dao;


import javafx.beans.property.IntegerProperty;

public interface Indexable {
	int getIndex();

	void setIndex(int index);

	IntegerProperty indexProperty();
}
