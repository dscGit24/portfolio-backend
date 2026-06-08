package com.disha.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank(message = "Name Required..")
    private String name;

    @Email(message = "Invalid Email..")
    @NotBlank(message = "Email Required..")
    private String email;

    @NotBlank(message = "Subject Required..")
    private String subject;

    @NotBlank(message = "Message Required..")
    private String message;
}
