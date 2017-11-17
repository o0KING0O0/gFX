package kr.co.giloh.fx.dao;


import java.io.File;

public interface Loadable<T> {
	public void load(T obj, File file);
}
