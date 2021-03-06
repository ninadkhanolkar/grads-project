package com.systems.wissen.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.systems.wissen.exception.WiseConnectRuntimeException;
import com.systems.wissen.service.StorageService;

@RestController
@RequestMapping(value = "/api/wiseconnect/v1/file")
@CrossOrigin(origins = { "*" })
public class UploadController {
	private static final String BIO_PIC = "bioPic";

	@Autowired
	StorageService storageService;

	private List<String> files = new ArrayList<>();
	private static final Logger logger = Logger.getLogger(UploadController.class);

	@PostMapping(value = "{type}")
	public ResponseEntity<String> handleUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("empId") String empId, @PathVariable String type) {
		String message = "";
		try {
			storageService.createFolder(empId);
			if (type.equals("resume")) {
				storageService.store(file, "resume");
			} else if (type.equals(BIO_PIC)) {
				storageService.store(file, BIO_PIC);
			} else {
				throw new WiseConnectRuntimeException("Unsupported file");
			}
			files.add(file.getOriginalFilename());
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			logger.error("Exception is : ", e);
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping(value = "{empId}/{type}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
			MediaType.ALL_VALUE, MediaType.APPLICATION_PDF_VALUE })
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String empId, @PathVariable String type)
			throws WiseConnectRuntimeException {
		Resource file;
		if (type.equals(BIO_PIC)) {
			file = storageService.loadFile(type + ".jpg", empId);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.contentType(MediaType.IMAGE_JPEG).body(file);
		} else {
			file = storageService.loadFile(type + ".pdf", empId);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.contentType(MediaType.APPLICATION_PDF).body(file);
		}

	}
}
