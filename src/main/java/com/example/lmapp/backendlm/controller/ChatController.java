package com.example.lmapp.backendlm.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lmapp.backendlm.service.OpenAIService;
import com.example.lmapp.dto.ChatRequest;

@RestController
@CrossOrigin(origins = "https://taupe-wisp-b654bd.netlify.app")
@RequestMapping("/api")
public class ChatController {

    private final OpenAIService openAIService;

    @Autowired
    public ChatController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chatWithAI(@RequestBody ChatRequest request) {
        String context = request.getContext();
        String question = request.getQuestion();

        // Combine context and question as a prompt
        String prompt = "Answer the question based on this context:\n\n" + context + "\n\nQuestion: " + question;
        String answer = openAIService.askQuestion(prompt);  // Use injected service here

        Map<String, String> response = new HashMap<>();
        response.put("answer", answer);
        return ResponseEntity.ok(response);
    }
}

