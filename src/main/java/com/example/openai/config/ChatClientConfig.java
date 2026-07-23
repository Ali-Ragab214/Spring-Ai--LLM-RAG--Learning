package com.example.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
@Bean
public ChatClient chatClient(ChatClient.Builder chatClientBuilder)
{
return  chatClientBuilder
        .defaultSystem("""
                        You are a senior Java Spring Boot developer.

                      You MUST only answer questions related to Java and Spring Boot.

                      If the user's question is not related to Java or Spring Boot,
                      DO NOT answer it.

                      Instead, reply exactly with:

                      "I'm sorry, I can only answer questions about Java and Spring Boot."
                        """)
        .defaultUser("How can You help me")//=>thats a default user message, but we can change it in the endpoint if we want to change the user message for a specific request
        .build();
}


}
