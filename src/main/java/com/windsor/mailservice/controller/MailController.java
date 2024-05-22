package com.windsor.mailservice.controller;

import com.windsor.mailservice.dto.Mail;
import com.windsor.mailservice.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping
    public void sendPlainText(@RequestBody Mail mail) {

        mailService.sendPlainText(mail);
    }
}
