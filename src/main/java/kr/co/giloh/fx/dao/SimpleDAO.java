package kr.co.giloh.fx.dao;


import javafx.collections.ObservableList;

/**
 * Comparable interface must be implemented for find() method.
 *
 * @param <T>
 */
public interface SimpleDAO<T extends Indexable & Newable<T> & Comparable<T>> extends Copyable<ObservableList<T>> {
	public boolean create(T itemToAdd);

	public boolean put(T itemToAdd);

	public ObservableList<T> read();

	public T read(int index);

	public boolean update(int index, T newItem);

	public boolean set(int index, T newItem);

	public boolean delete(int index);

	public boolean deleteAll();

	public boolean moveUp(int index);

	public boolean moveDown(int index);

	public void copy(ObservableList<T> objList);

	public T find(T obj);

	default void rearrange() {
		ObservableList<T> list = read();
		for (int i = 0; i < list.size(); i++) {
			T item = list.get(i);
			item.setIndex(i);
		}
	}


}
