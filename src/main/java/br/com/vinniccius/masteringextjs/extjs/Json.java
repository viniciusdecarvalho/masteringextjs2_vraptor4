package br.com.vinniccius.masteringextjs.extjs;

import java.util.List;

import javax.enterprise.inject.Specializes;

import br.com.caelum.vraptor.View;
import br.com.caelum.vraptor.validator.Message;

@Specializes
public interface Json extends View {

	Json from(Object object);

	Json include(String... names);
	
    Json exclude(String... names);
    
    Json excludeAll();

	Json success();

	Json success(boolean success);

	Json serialize();

	Json total(Long total);

	Json message(String message);
	
	Json badRequest(List<Message> errors);
	
}
