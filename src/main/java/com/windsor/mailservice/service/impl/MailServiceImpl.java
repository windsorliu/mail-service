package com.windsor.mailservice.service.impl;

import com.windsor.mailservice.dto.Mail;
import com.windsor.mailservice.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final JavaMailSender mailSender;

  @Value("${mail.service.sender}")
  private String sender;

  @Override
  public void sendPlainText(Mail mail) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(mail.getReceivers().toArray(new String[0]));
    message.setSubject(mail.getSubject());
    message.setText(mail.getContent());
    message.setFrom(sender);

    mailSender.send(message);
  }
}
