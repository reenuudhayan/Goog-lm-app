package com.example.lmapp.dto;


public class ChatRequest {
    private String question;
    private String fileName;
    private String context;

    public ChatRequest() {}

    public ChatRequest(String question, String fileName, String context) {
        this.question = question;
        this.fileName = fileName;
        this.context = context;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
