package kr.co.giloh.persistence;


import javafx.collections.ObservableList;

public interface DataAccessObject<T> {
	boolean create(T itemToAdd);

	ObservableList<T> read();

	boolean update(T oldItem, T newItem);

	boolean delete(T itemToDel);

}
