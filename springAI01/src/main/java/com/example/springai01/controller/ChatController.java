package com.example.springai01.controller;


import com.example.springai01.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;
    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;

    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatClient
                .prompt()
//                .advisors(new TokenUsageAuditAdvisor())
                .user(message)
                .call().content();

    }
}

