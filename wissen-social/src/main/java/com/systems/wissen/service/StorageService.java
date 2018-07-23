package com.systems.wissen.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class StorageService {
	private final Path rootLocation = Paths.get("upload-dir");
	private Path folderPath;

	public void store(MultipartFile file,String name) {
		try {
			Files.copy(file.getInputStream(), this.folderPath.resolve(name+"."+file.getContentType().substring(file.getContentType().lastIndexOf('/')+1)));
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("FAIL!");
		}
	}

	public Resource loadFile(String filename,String folder) {
		try {
			Path file = rootLocation.resolve(folder+"/"+filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

	public void createFolder(String folder) {
		try {
			folderPath=Paths.get(rootLocation.toString(), folder);
			Files.createDirectory(folderPath);
		} catch (FileAlreadyExistsException e) {
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
}
