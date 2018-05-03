package kr.co.giloh.fx.sync;


import kr.co.giloh.persistence.file.AbstractDAO;
import kr.co.giloh.model.dao.Copyable;
import kr.co.giloh.persistence.DataTransferObject;
import kr.co.giloh.model.dao.Emptiable;

public class BaseSync<T extends DataTransferObject<T> & Copyable<T> & Emptiable, D extends AbstractDAO<T>> implements Synchronizable<T, D> {


	public boolean sync(T source, D targetDAO) {
		return synchronize(source, targetDAO);
	}

}
