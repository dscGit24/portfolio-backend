package com.disha.portfolio.controller;

import com.disha.portfolio.dto.ContactRequest;
import com.disha.portfolio.dto.ContactResponse;
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
public class ContactController {

    private final EmailService emailService;
//
//    @PostMapping("/test")
//    public ResponseEntity<String> testEmail() {
//
//        emailService.sendTestEmail();
//
//        return ResponseEntity.ok("Email sent");
//    }

    @GetMapping("/mail-test")
    public String mailTest() throws Exception {
        emailService.sendAutoReply(
                "Disha",
                "yourpersonalemail@gmail.com",
                "SMTP Test"
        );
        return "sent";
    }

    private final ContactService service;

    @PostMapping
    public ResponseEntity<ContactResponse> submitContact(@Valid @RequestBody ContactRequest request) throws MessagingException {
        service.saveContact(request);
        return ResponseEntity.ok(
                new ContactResponse(
                        "success",
                        "Message Submitted Successfully.."
                )
        );
    }
}
