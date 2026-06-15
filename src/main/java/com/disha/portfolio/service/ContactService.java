package com.disha.portfolio.service;

import com.disha.portfolio.dto.ContactRequest;
import com.disha.portfolio.entity.ContactMessage;
import com.disha.portfolio.repository.ContactMessageRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final EmailService emailService;

    private final ContactMessageRepository repository;

    public void saveContact(ContactRequest request) throws MessagingException {
        ContactMessage message = ContactMessage.builder()
                .name(request.getName())
                .email(request.getEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(message);

//        try {
//
//            emailService.sendContactNotification(
//                    request.getName(),
//                    request.getEmail(),
//                    request.getSubject(),
//                    request.getMessage()
//            );
//            emailService.sendAutoReply(
//                    request.getName(),
//                    request.getEmail(),
//                    request.getSubject()
//            );
//
//        } catch (Exception e) {
//
//            log.error(
//                    "Email notification failed for contact: {}",
//                    request.getEmail(),
//                    e
//            );
//
//        }
    }
}
