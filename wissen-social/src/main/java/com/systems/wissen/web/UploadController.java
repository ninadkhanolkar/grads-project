package com.systems.wissen.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.systems.wissen.service.StorageService;

@RestController
@RequestMapping(value="/api/wiseconnect/v1/file")
@CrossOrigin(origins = { "*" })
public class UploadController {
	@Autowired
	StorageService storageService;
 
	List<String> files = new ArrayList<String>();
 
	@PostMapping(value="{type}")
	public ResponseEntity<String> handleUpload(@RequestParam("file") MultipartFile file,@RequestParam("empId") String empId,@PathVariable String type) {
		String message = "";
		try {
//			storageService.deleteAll();
//			storageService.init();
			storageService.createFolder(empId);
			if(type.equals("resume")) {
				storageService.store(file,"resume");
			}
			else if(type.equals("bioPic")) {
				storageService.store(file,"bioPic");
			}
			else {
				throw new Exception("Unsupported file");
			}
			files.add(file.getOriginalFilename());
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			System.out.println(e);
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
 
//	@GetMapping(value="{type}")
//	public ResponseEntity<List<String>> getListFiles(Model model) {
//		List<String> fileNames = files
//				.stream().map(fileName -> MvcUriComponentsBuilder
//						.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
//				.collect(Collectors.toList());
// 
//		return ResponseEntity.ok().body(fileNames);
//	}
 
	@GetMapping(value="{empId}/{type}",produces= {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE,MediaType.ALL_VALUE})
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String empId,@PathVariable String type) {
		Resource file = storageService.loadFile(type+".jpeg",empId);
		byte[] bytes= {};
		try {
			bytes = StreamUtils.copyToByteArray(file.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.contentType(MediaType.IMAGE_JPEG)
				.body(file);
//		return file;
	}
}


