package com.example.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient chatclient) {

        this.chatClient = chatclient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message )
    {
      return chatClient
              .prompt()
              //we make a default system method in the constructor, but we can also change it in the endpoint if we want to change the system prompt for a specific request
//              .system("""
//                      You are a senior Java Spring Boot developer.
//
//                      You MUST only answer questions related to Java and Spring Boot.
//
//                      If the user's question is not related to Java or Spring Boot,
//                      DO NOT answer it.
//
//                      Instead, reply exactly with:
//
//                      "I'm sorry, I can only answer questions about Java and Spring Boot."
//                      """)
              .user(message)
              .call()
              .content();
    }
//عملنا الكونفيجوربشن بتاعه الشات كلاينت في كلاس لوحدها واديناها ديفولت سيستم مسد وديفولت يوزر مسج

}

//why we used ChatClient.Builder instead of ChatClient in the constructor?
//We used ChatClient.Builder instead of ChatClient in the constructor to allow for more flexible configuration of the ChatClient instance. The Builder pattern is a design pattern that provides a way to construct complex objects step by step, allowing for customization and configuration before the final object is created. By using ChatClient.Builder, we can set various parameters and options for the ChatClient before building it, which can be useful in scenarios where we want to customize the behavior of the ChatClient based on different requirements
