package com.example.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prompt-template")
public class PromptTemplateController {
private final ChatClient chatClient;

    public PromptTemplateController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
//    String promptTemplate =
//            """
//                    A customer named {customerName} has sent the following message:
//                    "{customerMessage}
//
//                    write a polite and helpful email response addressing the issue .
//                    maintain a professional tone and provide clear instructions or solutions to resolve the customer's concern.
//
//                    Respond as if you are writing the email body only. dont include subject signature
//                    """;

    @Value("classpath:promptTemplates/userPromptTemplate.st")
    Resource userPromptTemplate;

    @GetMapping("/email")
    public String emailResponse(@RequestParam("customerName") String customerName,
                                @RequestParam("customerMessage") String customerMessage)
{
        return chatClient
                .prompt()
                .system("""
                        You are a customer support representative.
                        which helps drafting email responses to customers based on their messages.
                        """)
                .user(promptUserSpec ->
                        promptUserSpec.text(userPromptTemplate)
                                .param("customerName", customerName)
                .param("customerMessage", customerMessage))
                .call()
                .content();
}

    // You can add methods here to handle requests related to prompt templates
// لو عندي اكتر من برومت تيمبليت هحط كل واحد في فايل لوحده
}
