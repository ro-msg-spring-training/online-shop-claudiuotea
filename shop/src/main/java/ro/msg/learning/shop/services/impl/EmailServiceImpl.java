package ro.msg.learning.shop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.models.Users;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {
    private final JavaMailSender emailSender;
    @Value("${email.subject}")
    private String emailSubject;
    @Value("${email.body}")
    private String emailBody;


    //TODO: SPEL EXPRESSION
    public void sendPlainMessage(
            Users user
    ) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@loveinternship.com");
        message.setTo(user.getEmail());
        message.setSubject(emailSubject);
        message.setText(emailBody);
        emailSender.send(message);
    }

    public void sendHtmlMessage(
        Users user
    ) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@loveinternship.com");
        helper.setTo(user.getEmail());
        helper.setSubject(emailSubject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(emailBody,"text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        emailSender.send(message);
    }
}
