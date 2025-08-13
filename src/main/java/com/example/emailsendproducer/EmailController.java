package com.example.emailsendproducer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailService emailServier;

    public EmailController(EmailService emailServier) {
        this.emailServier = emailServier;
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailRequestDto sendEmailRequestDto) {
        emailServier.sendEmail(sendEmailRequestDto);
        return ResponseEntity.ok("이메일 발송 요청 완료");
    }
}
