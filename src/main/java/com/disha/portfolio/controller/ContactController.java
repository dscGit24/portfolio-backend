package com.disha.portfolio.controller;

import com.disha.portfolio.dto.ContactRequest;
import com.disha.portfolio.service.ContactService;
import com.disha.portfolio.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

//    private final EmailService emailService;
//
//    @PostMapping("/test")
//    public ResponseEntity<String> testEmail() {
//
//        emailService.sendTestEmail();
//
//        return ResponseEntity.ok("Email sent");
//    }

    private final ContactService service;

    @PostMapping
    public ResponseEntity<String> submitContact(@Valid @RequestBody ContactRequest request) throws MessagingException {
        service.saveContact(request);
        return  ResponseEntity.ok("Message Submitted Successfully..");
    }
}
