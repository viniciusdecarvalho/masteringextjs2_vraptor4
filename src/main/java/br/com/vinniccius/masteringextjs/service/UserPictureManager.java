package br.com.vinniccius.masteringextjs.service;

import br.com.caelum.vraptor.observer.upload.UploadedFile;

public interface UserPictureManager {

	String saveImage(String name, UploadedFile imagem);

	void deleteImage(String name);

}