package com.windsor.mailservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windsor.mailservice.dto.Mail;
import com.windsor.mailservice.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailListener {

    private final ObjectMapper objectMapper;
    private final MailService mailService;

    @RabbitListener(queues = "${mail.service.send.to.user.queue}")
    public void handleMessage(String message) {
        Mail mail = Mail.builder().build();
        try {
            mail = objectMapper.readValue(message, Mail.class);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse mail message", e);
        }

        mailService.sendPlainText(mail);
    }
}
