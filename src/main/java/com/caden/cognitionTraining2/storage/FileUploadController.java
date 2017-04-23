package com.caden.cognitionTraining2.storage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.caden.cognitionTraining2.dip.DIPService;

import java.io.IOException;
import java.util.stream.Collectors;


@Controller
public class FileUploadController {

	private final StorageService storageService;
	private final DIPService dipService;

	@Autowired
	public FileUploadController(StorageService storageService, DIPService dipService) {
		this.storageService = storageService;
		this.dipService = dipService;
	}

	@GetMapping("/upload")
	public String listUploadedFiles(Model model, @RequestParam("filename") String filename) throws IOException {

		model.addAttribute("files",
				storageService.loadAll()
						.map(path -> MvcUriComponentsBuilder
								.fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
								.build().toString())
						.collect(Collectors.toList()));
		model.addAttribute("filename", filename);

		return "uploadForm";
	}

	@GetMapping("/files/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename + ".jpg");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/reset")
	public String deleteFiles() {
		storageService.deleteAll();
		storageService.init();
		return "uploadForm";
	}

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Model model) {

		// Do some file type check here
		String filename = file.getOriginalFilename();
		
		// is JPG file?
		if (!filename.substring(filename.lastIndexOf(".")  + 1, filename.length()).equalsIgnoreCase("jpg")) {
			System.out.println("======== Not JPG FILE ========");
			redirectAttributes.addFlashAttribute("message",
					"Please uploade JPG file!");
			return "redirect:/reset";
		}
		
		storageService.store(file);
		dipService.process(600, 600, filename);
		redirectAttributes.addFlashAttribute("filename", filename);
		return "redirect:/puzzle";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<Object> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		System.out.println("======== handleStorageFileNotFound ========");
		return ResponseEntity.notFound().build();
	}
}
