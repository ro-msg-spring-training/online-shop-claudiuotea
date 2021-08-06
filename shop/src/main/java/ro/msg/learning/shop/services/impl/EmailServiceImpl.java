package ro.msg.learning.shop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.models.Users;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {
    private final Environment env;
    private final JavaMailSender emailSender;

    @Value("${email.subject}")
    private String emailSubject;

    public void sendPlainMessage(
            Users user, OrderDTO orderDTO
    ) {
        String emailBody = env.getProperty("email.body");
        emailBody = parseBody(emailBody,orderDTO);
        System.out.println(emailBody);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@loveinternship.com");
        message.setTo(user.getEmail());
        message.setSubject(emailSubject);
        message.setText(emailBody);
        emailSender.send(message);
    }

    private String parseBody(String emailBody,Object object){
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(emailBody,new TemplateParserContext());
        StandardEvaluationContext context = new StandardEvaluationContext(object);
        return (String) expression.getValue(context);
    }

    public void sendHtmlMessage(
        Users user, OrderDTO orderDTO
    ) throws RuntimeException {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@loveinternship.com");
            helper.setTo(user.getEmail());
            helper.setSubject(emailSubject);

            String emailBody = env.getProperty("email.body");
            emailBody =  this.parseBody(emailBody, orderDTO);
            System.out.println(emailBody);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(emailBody,"text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("There is an error with mail service.");
        }

    }
}
