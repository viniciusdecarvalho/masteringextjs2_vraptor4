	package br.com.vinniccius.masteringextjs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.Validator;
import br.com.vinniccius.masteringextjs.dto.DtoUserForm;
import br.com.vinniccius.masteringextjs.extjs.Json;
import br.com.vinniccius.masteringextjs.interceptor.Login;
import br.com.vinniccius.masteringextjs.interceptor.UserInfo;
import br.com.vinniccius.masteringextjs.model.Menu;
import br.com.vinniccius.masteringextjs.model.User;
import br.com.vinniccius.masteringextjs.repository.Groups;
import br.com.vinniccius.masteringextjs.repository.Users;
import br.com.vinniccius.masteringextjs.service.UserPictureManager;

@Controller
public class SecurityController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	private final Groups groups;
	private final Users users;
	private final UserInfo userInfo;
	private final UserPictureManager pictureManager;
	
	/**
	 * @deprecated cdi eyes only
	 */
	protected SecurityController() {
		this(null, null, null, null, null, null);
	}
	
	@Inject
	public SecurityController(Result result, Validator validator, Groups groups, Users users, UserInfo userInfo, UserPictureManager pictureManager) {
		super(result, validator);
		this.groups = groups;
		this.users = users;
		this.userInfo = userInfo;
		this.pictureManager = pictureManager;
	}

	@Post("security/users/login")
	public Json login(@Valid Login login) {		
		User user = users.login(login);
		userInfo.setUser(user);
		List<Menu> rootMenu = users.getRootMenu(user);
		userInfo.setRootMenu(rootMenu);
		logger.debug(userInfo.toString());
		return json("Usuario autenticado com sucesso!", true);
	}
	
	@Post("security/verify")
	public Json verify(String module) {
		logger.debug("module request {}", module);
		return json(null, true);
	}
	
	@Get("security/users/logout")
	public Json logout() {
		userInfo.logout();
		return json("Logout do usuario com sucesso", true);
	}
	
	@Get("security/users/menu")
	public Json menu() {	
		return json(userInfo.getRootMenu());
	}
	
	@Transactional
	@UploadSizeLimit(sizeLimit=5 * 1024 * 1024, fileSizeLimit=5 * 1024 * 1024)//5mb	
	@Post({"users", "users/{id}"})
	public Json users(DtoUserForm user, UploadedFile picture) {
		validate(user.getUserModel());
		if (hasErrors()) {
			return json().badRequest(getErrors());
		}

		String pictureName = pictureManager.saveImage(user.getUserName(), picture);
		user.setPicture(pictureName);
		users.save(user.getUserModel());
		
		return json("Usuario salvo com sucesso.", true);
		//on save image only
//		use(HttpResult.class).addHeader("Content-Type", "text/html")
//							 .body("{'success':true}");
	}
	
	@Get("users")
	public Json users(Integer id) {
		return json(users.list());
	}
	
	@Get("users/{id}")
	public Json user(Integer id) {
		return json(users.get(id));
	}
	
	@Transactional
	@Delete("users/{id}")
	public Json deleteUser(Integer id) {
		users.delete(id);
		return json("Usuario removido com sucesso!", true);
	}
	
	@Get("groups")
	public Json groups() {
		return json(groups.list());
	}
	
	@Get("security/menu/permissions/{id}")
	public Json permissions(Integer id) {
		List<Menu> permissions = groups.rootMenu(id);
		return json(permissions);
	}
	
}
