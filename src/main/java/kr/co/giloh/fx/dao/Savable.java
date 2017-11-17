package kr.co.giloh.fx.dao;


import java.io.File;

public interface Savable<T> {
	public void save(T obj, File file);
}
