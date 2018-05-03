package kr.co.giloh.persistence;

import kr.co.giloh.model.dao.Copyable;
import kr.co.giloh.model.dao.Indexable;
import kr.co.giloh.model.dao.Newable;

public interface DataTransferObject<T> extends Indexable, Newable<T>, Comparable<T>, Copyable<T> {

}
