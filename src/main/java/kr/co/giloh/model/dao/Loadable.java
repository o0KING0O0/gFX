package kr.co.giloh.model.dao;


import java.io.File;

public interface Loadable<T> {
	public void load(T obj, File file);
}
