package br.com.vinniccius.masteringextjs.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface Repository<T, I extends Serializable> {

	List<T> list();

	List<T> list(Integer start, Integer limit);
	
	List<T> list(T model);
	
	List<T> list(T model, Integer start, Integer limit);
	
	void save(T model);

	void saveAll(Collection<T> models);

	void delete(I id);	
	
	void delete(T model);

	void deleteAll(Collection<T> models);

	boolean contains(T model);
	
    T load(I id);
	
	T get(I id);
	
	T findBy(T model);
	
	Long total();
	
}
