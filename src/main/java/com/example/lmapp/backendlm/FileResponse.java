package com.example.lmapp.backendlm;

public class FileResponse {
    private String fileName;       // Stored file name (with UUID)
    private String originalName;   // Original file name uploaded

    public FileResponse(String fileName, String originalName) {
        this.fileName = fileName;
        this.originalName = originalName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
}
