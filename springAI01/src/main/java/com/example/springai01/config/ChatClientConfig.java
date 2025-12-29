package com.example.springai01.config;

import com.example.springai01.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        ChatOptions chatOptions =  ChatOptions.builder()
                .temperature(0.8).build();
        return chatClientBuilder
//                .defaultAdvisors(new SimpleLoggerAdvisor())
//                .defaultAdvisors(new TokenUsageAuditAdvisor())
                .defaultOptions(chatOptions)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .defaultSystem("""
                        You are an internal HR assistant. Your role is to help \s
                        employees with their HR policies. such as\s
                        leave policies, work hours, benefits, pay, and code of conduct\s

                        If a user asks for help anything outside of these topics\s
                        Kindly inform that you can only assist with queries related to HR policies\s
                        """)
                .build();
    }
}
