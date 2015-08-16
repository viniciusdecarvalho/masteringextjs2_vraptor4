package br.com.vinniccius.masteringextjs.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.observer.upload.UploadedFile;

import com.google.common.base.Strings;

public class UserPictureManagerImpl implements UserPictureManager {

	private final String rootPath;

	public UserPictureManagerImpl(String rootPath) {
		this.rootPath = rootPath;
	}

	@Override
	public String saveImage(String name, UploadedFile imagem) {
		
		if (Strings.isNullOrEmpty(name) || imagem == null)
			return null;
		
	    String fullName = name + "." + FilenameUtils.getExtension(imagem.getFileName());
		
		try {
			IOUtils.copyLarge(imagem.getFile(), new FileOutputStream(new File(rootPath, fullName)));
		} catch (IOException e) {
			String message = String.format("Falha na tentativa de salvar a imagem %s", imagem.getFileName());
			throw new RuntimeException(message);
		}
		
		return fullName;
	}
	
	@Override
	public void deleteImage(String name) {
		if (!Strings.isNullOrEmpty(name)) {
			File file = new File(rootPath, name);
			if (file.exists()) {
				file.delete();
			}
		}
	}

}
