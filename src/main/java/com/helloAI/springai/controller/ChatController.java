package com.helloAI.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    public ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String message(@RequestParam("message") String message) {

        return this.chatClient
                 .prompt()
                 .system("""
                        You are a HR assistant. Answer queries only related to HR policies. If the users
                        ask anything else you respond that you can only help with HR policies related questions.
                        """)
                 .user(message)
                 .call()
                 .content();
    }
}
