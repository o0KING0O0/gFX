package kr.co.giloh.fx.dao;


import javafx.collections.ObservableList;

public interface DataAccessObject<T> {
	public boolean create(T itemToAdd);

	public ObservableList<T> read();

	public boolean update(T oldItem, T newItem);

	public boolean delete(T itemToDel);

}
