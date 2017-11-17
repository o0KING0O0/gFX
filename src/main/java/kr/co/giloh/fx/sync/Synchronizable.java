package kr.co.giloh.fx.sync;


import kr.co.giloh.fx.dao.BasicDAO;
import kr.co.giloh.fx.dao.Copyable;
import kr.co.giloh.fx.dao.DataTransferObject;
import kr.co.giloh.fx.dao.Emptiable;

public interface Synchronizable<T extends DataTransferObject<T> & Copyable<T> & Emptiable, D extends BasicDAO<T>> {
	/**
	 * @param source
	 * @param targetDAO : DAO will have source instance. <br/>
	 *                  if item among list of target DAO don't be matched by target, list add source instance(not copy).<br/>
	 *                  if item among list of target DAO is matched by source, item among list is replaced with source by create() method of DAO.
	 */
	default public boolean synchronize(T source, D targetDAO) {
		boolean updated = false;
		if (source.isEmpty()) {
			return false;
		}

		for (int i = 0; i < targetDAO.read().size(); i++) {
			T iT = targetDAO.read().get(i);
			if (source.compareTo(iT) == 0) {
				source.copy(iT);
				targetDAO.set(i, source);
				updated = true;
			}
		}

		if (!updated) {
			targetDAO.put(source);
			updated = true;
		}

		return updated;
	}


	public boolean sync(T source, D targetDAO);

}
