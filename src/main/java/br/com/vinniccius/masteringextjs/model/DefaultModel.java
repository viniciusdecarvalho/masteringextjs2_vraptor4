package br.com.vinniccius.masteringextjs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DefaultModel<ID extends Serializable> implements Model<ID> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private ID id;
	
	@Override
	public ID getId() {
		return id;
	}

	@Override
	public void setId(ID id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Model<?> other = (Model<?>) obj;
		return getId().equals(other.getId());
	}
	
	@Override
	public Object clone() {
//		try {
//			Model<Long> bean = getClass().newInstance();
//			BeanUtils.copyProperties(this, bean);
//		} catch (BeansException e) {
//			e.printStackTrace();
//			throw new IllegalStateException(e);
//		}
//		return bean;
		return clone();
	}

//	@Override
	public boolean isNew() {
		return id == null;
	}

}
