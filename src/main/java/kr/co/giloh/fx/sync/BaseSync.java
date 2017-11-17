package kr.co.giloh.fx.sync;


import kr.co.giloh.fx.dao.BasicDAO;
import kr.co.giloh.fx.dao.Copyable;
import kr.co.giloh.fx.dao.DataTransferObject;
import kr.co.giloh.fx.dao.Emptiable;

public class BaseSync<T extends DataTransferObject<T> & Copyable<T> & Emptiable, D extends BasicDAO<T>> implements Synchronizable<T, D> {


	public boolean sync(T source, D targetDAO) {
		return synchronize(source, targetDAO);
	}

}
