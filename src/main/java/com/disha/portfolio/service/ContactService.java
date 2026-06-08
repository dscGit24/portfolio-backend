package com.disha.portfolio.service;

import com.disha.portfolio.dto.ContactRequest;
import com.disha.portfolio.entity.ContactMessage;
import com.disha.portfolio.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMessageRepository repository;

    public void saveContact(ContactRequest request){
        ContactMessage message = ContactMessage.builder()
                .name(request.getName())
                .email(request.getEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(message);
    }
}
