package com.systems.wissen.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.systems.wissen.exception.WiseConnectRuntimeException;

@Service
@Transactional
public class StorageService {
	private static final String COULD_NOT_INITIALIZE_STORAGE = "Could not initialize storage!";
	private final Path rootLocation = Paths.get("upload-dir");
	private Path folderPath;
	private static final Logger logger = Logger.getLogger(StorageService.class);

	public void store(MultipartFile file, String name) throws WiseConnectRuntimeException {
		try {
			if (file != null)
				Files.copy(file.getInputStream(), this.folderPath.resolve(
						name + "." + file.getContentType().substring(file.getContentType().lastIndexOf('/') + 1)));
		} catch (Exception e) {
			logger.error("Exception is : ", e);
			throw new WiseConnectRuntimeException("FAIL !");
		}
	}

	public Resource loadFile(String filename, String folder) throws WiseConnectRuntimeException {
		try {
			Path file = rootLocation.resolve(folder + "/" + filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new WiseConnectRuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new WiseConnectRuntimeException("FAIL!");
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() throws WiseConnectRuntimeException {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new WiseConnectRuntimeException(COULD_NOT_INITIALIZE_STORAGE);
		}
	}

	public void createFolder(String folder) throws WiseConnectRuntimeException {
		try {
			folderPath = Paths.get(rootLocation.toString(), folder);
			Files.createDirectory(folderPath);
		} catch (IOException g) {
			throw new WiseConnectRuntimeException(COULD_NOT_INITIALIZE_STORAGE);
		}
	}
}
