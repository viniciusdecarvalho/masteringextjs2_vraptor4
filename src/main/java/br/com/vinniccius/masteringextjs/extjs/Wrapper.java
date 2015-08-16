package br.com.vinniccius.masteringextjs.extjs;

import java.util.ArrayList;
import java.util.Collection;

public class Wrapper {

	private Boolean success;
	private String msg;
	private Long total;
	private Object data;

	public void setData(Object data) {
		if (data instanceof Collection) {
			this.data = new ArrayList<Object>((Collection<?>) data);
		} else {
			this.data = data;
		}
	}
	
	public Object getData() {
		return data;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getMessage() {
		return msg;
	}

	public void setMessage(String message) {
		this.msg = message;
	}
	
}
