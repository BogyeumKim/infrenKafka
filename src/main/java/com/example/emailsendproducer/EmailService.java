package com.example.emailsendproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final KafkaTemplate<String ,String> kafkaTemplate;

    public EmailService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmail(SendEmailRequestDto request) {
        EmailSendMessage emailSendMessage = new EmailSendMessage(request.getFrom(), request.getTo(), request.getSubject(), request.getBody());

        this.kafkaTemplate.send("email.send", toJson(emailSendMessage));
    }

    private String toJson(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message = objectMapper.writeValueAsString(objectMapper);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json 직렬화 실패");
        }
        return message;
    }
}
