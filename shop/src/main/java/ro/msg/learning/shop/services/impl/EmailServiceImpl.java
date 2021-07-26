package ro.msg.learning.shop.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
@AllArgsConstructor
public class EmailServiceImpl {
    private final JavaMailSender emailSender;

    public void sendPlainMessage(
            String to, String subject, String body
    ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@loveinternship.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }

    public void sendHtmlMessage(
            String to, String subject, String body
    ) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@loveinternship.com");
        helper.setTo(to);
        helper.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body,"text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        emailSender.send(message);
    }
}
