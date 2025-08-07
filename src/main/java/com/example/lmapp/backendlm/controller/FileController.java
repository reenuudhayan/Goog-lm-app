package com.example.lmapp.backendlm.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.lmapp.backendlm.FileResponse;
import com.example.lmapp.backendlm.service.FileStorageService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://taupe-wisp-b654bd.netlify.app") 
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;

    public static final String UPLOAD_DIR = "uploads";

   @PostMapping("/upload")
public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
    
        String storedFileName = fileStorageService.store(file); 
        String originalFileName = file.getOriginalFilename();
        FileResponse response = new FileResponse(storedFileName, originalFileName);

        return ResponseEntity.ok(response);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

@GetMapping("/view/{filename}")
public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
    Path filePath = Paths.get("uploads").resolve(filename).normalize(); // FIXED
    Resource resource = new UrlResource(filePath.toUri());

    if (!resource.exists() || !resource.isReadable()) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
}


}
