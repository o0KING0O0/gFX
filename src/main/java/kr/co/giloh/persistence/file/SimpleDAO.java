package kr.co.giloh.persistence.file;


import javafx.collections.ObservableList;
import kr.co.giloh.model.dao.Copyable;
import kr.co.giloh.model.dao.Indexable;
import kr.co.giloh.model.dao.Newable;

/**
 * Comparable interface must be implemented for find() method.
 *
 * @param <T>
 */
public interface SimpleDAO<T extends Indexable & Newable<T> & Comparable<T>> extends Copyable<ObservableList<T>> {
	boolean create(T itemToAdd);

	boolean put(T itemToAdd);

	ObservableList<T> read();

	T read(int index);

	boolean update(int index, T newItem);

	boolean set(int index, T newItem);

	boolean delete(int index);

	boolean deleteAll();

	boolean moveUp(int index);

	boolean moveDown(int index);

	void copy(ObservableList<T> objList);

	T find(T obj);

	default void rearrange() {
		ObservableList<T> list = read();
		for (int i = 0; i < list.size(); i++) {
			T item = list.get(i);
			item.setIndex(i);
		}
	}


}
