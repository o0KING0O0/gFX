package kr.co.giloh.fx.dao;


import javafx.beans.property.IntegerProperty;

public interface Indexable {
	public int getIndex();

	public void setIndex(int index);

	public IntegerProperty indexProperty();
}
