package com.disha.portfolio.controller;

import com.disha.portfolio.dto.ContactRequest;
import com.disha.portfolio.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @PostMapping
    public ResponseEntity<String> submitContact(@Valid @RequestBody ContactRequest request){
        service.saveContact(request);
        return  ResponseEntity.ok("Message Submitted Successfully..");
    }
}
