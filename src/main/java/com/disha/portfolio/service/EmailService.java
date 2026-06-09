package com.disha.portfolio.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EmailService {

//    public void sendTestEmail() {
//
//        SimpleMailMessage message =
//                new SimpleMailMessage();
//
//        message.setTo("chotaidisha24@gmail.com");
//        message.setSubject("Portfolio Backend Test");
//        message.setText(
//                "Congratulations! Gmail SMTP is working."
//        );
//
//        mailSender.send(message);
//    }

    private final JavaMailSender mailSender;

    String submittedAt =
            LocalDateTime.now()
                    .format(
                            DateTimeFormatter.ofPattern(
                                    "dd MMM yyyy hh:mm a"

                            )
                    );

    private static final String FROM_NAME =
            "Disha Chotai Portfolio";

    private static final String FROM_EMAIL =
            "chotaidisha24@gmail.com";

    private InternetAddress getSenderAddress()
            throws MessagingException {

        try {

            return new InternetAddress(
                    FROM_EMAIL,
                    FROM_NAME
            );

        } catch (UnsupportedEncodingException e) {

            throw new MessagingException(
                    "Failed to create sender address",
                    e
            );
        }
    }
    public void sendAutoReply(
            String name,
            String email,
            String subject)
            throws MessagingException{

        MimeMessage mimeMessage =
                mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, true);

        helper.setTo(email);
        helper.setFrom(
                getSenderAddress()
        );
        helper.setSubject(
                "Thank you for reaching out | Disha Chotai"
        );

        String html =
                buildAutoReplyTemplate(
                        name,
                        subject
                );

        helper.setText(html, true);

        mailSender.send(mimeMessage);
    }

    public void sendContactNotification(
            String name,
            String email,
            String subject,
            String message)
            throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, true);

        helper.setTo("chotaidisha24@gmail.com");
        helper.setFrom(
                getSenderAddress()
        );
        helper.setSubject("📩 New Portfolio Contact - " + subject);

        String html = buildOwnerTemplate(
                name,
                email,
                subject,
                message,
                submittedAt
        );

        helper.setText(html, true);

        mailSender.send(mimeMessage);
    }

    private String buildOwnerTemplate(
            String name,
            String email,
            String subject,
            String message,
            String submittedAt) {

        return """
                <div style='font-family:Segoe UI,sans-serif;
                            max-width:700px;
                            margin:auto;'>

                    <div style='background:#2563eb;
                                color:white;
                                padding:25px;
                                text-align:center;'>

                        <h1>Portfolio Inquiry</h1>
                        <p>New Contact Submission</p>

                    </div>

                    <div style='padding:25px;'>

                        <table style='width:100%%;
                                       border-collapse:collapse;'>

                            <tr>
                                <td style='padding:12px;
                                           border:1px solid #ddd;'>
                                    <strong>Name</strong>
                                </td>

                                <td style='padding:12px;
                                           border:1px solid #ddd;'>
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td style='padding:12px;
                                           border:1px solid #ddd;'>
                                    <strong>Email</strong>
                                </td>

                                <td style='padding:12px;
                                           border:1px solid #ddd;'>
                                    %s
                                </td>
                            </tr>

                            <tr>
                                <td style='padding:12px;
                                           border:1px solid #ddd;'>
                                    <strong>Subject</strong>
                                </td>

                                <td style='padding:12px;
                                           border:1px solid #ddd;'>
                                    %s
                                </td>
                            </tr>
                        
                            <tr>
                                <td style='padding:12px;
                                           border:1px solid #ddd;'>
                                    <strong>Received</strong>
                                </td>
                
                                <td style='padding:12px;
                                           border:1px solid #ddd;'>
                                    %s
                                </td>
                            </tr>
                        
                        </table>

                        <h3 style='margin-top:30px;'>
                            Message
                        </h3>

                        <div style='background:#f8fafc;
                                    padding:20px;
                                    border-left:4px solid #2563eb;'>
    
                            %s

                        </div>

                    </div>

                </div>
                """
                .formatted(
                        name,
                        email,
                        subject,
                        submittedAt,
                        message
                );
    }

    private String buildAutoReplyTemplate(
            String name,
            String subject) {

        return """
            <div style='font-family:Segoe UI,sans-serif;
                        max-width:700px;
                        margin:auto;
                        background:#ffffff;'>

                <div style='background:#2563eb;
                            color:white;
                            padding:20px;
                            text-align:center;'>

                    <h1 style='margin:0;'>
                        Disha Chotai Portfolio
                    </h1>

                </div>

                <div style='padding:30px;'>

                    <h2>
                        Hello %s,
                    </h2>

                    <p>
                        Thank you for taking the time to
                        contact me through my portfolio website.
                    </p>

                    <p>
                        I have successfully received
                        your message regarding:
                    </p>

                    <div style='background:#eff6ff;
                                border-left:4px solid #2563eb;
                                padding:15px;
                                border-radius:8px;
                                margin:20px 0;'>

                        <strong>%s</strong>

                    </div>

                    <p>
                        I appreciate your interest and the opportunity to connect.
                        Whether you're reaching out about a job opportunity, internship, collaboration,
                        freelance project, or simply a professional discussion, I value every message I receive.
                        
                    </p>

                    <p>
                        I typically respond within
                        <strong>24 hours</strong>.
                    </p>
                    
                    <p>
                        For your reference, a copy of your submission has been safely received by my system.
                    </p>

                    <p>
                        In the meantime, feel free to
                        explore my work and connect with me:
                    </p>

                    <ul>
                        <li>
                            <a href="https://github.com/dscGit24">
                                GitHub
                            </a>
                        </li>
                
                        <li>
                            <a href="https://linkedin.com/in/disha-chotai-164350341">
                                LinkedIn
                            </a>
                        </li>
                
                        <li>
                            <a href="https://dishachotai.in">
                                Portfolio Website
                            </a>
                        </li>
                    </ul>

                    <p>
                        Thank you once again for reaching out.
                        I look forward to connecting with you and discussing how we may work together.
                    </p>
                    
                    <p>Warm Regards,</p>
                    
                    <strong>
                        Disha Chotai
                    </strong>

                    <br>

                    Java Full Stack Developer

                    <br>

                    Ahmedabad, Gujarat
                    
                    <p>
                        <i>
                            "Building scalable applications with Java, Spring Boot, React, and modern web technologies."
                        </i>
                    <p>

                </div>

                <div style='background:#f8fafc;
                            padding:20px;
                            text-align:center;
                            color:#64748b;
                            font-size:13px;'>

                    This is an automated confirmation email.
                    Please do not reply directly to this message.

                </div>

            </div>
            """
                .formatted(
                        name,
                        subject
                );
    }
}