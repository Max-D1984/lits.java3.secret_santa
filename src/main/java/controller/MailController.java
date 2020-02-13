package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;

@RestController
public class MailController {

    @RequestMapping(
            value = "/mail",
            method = RequestMethod.GET)
    public ResponseEntity getUser(
            @RequestParam String mailTo,
            @RequestParam String username,
            @RequestParam String password) {

        Properties props = new Properties();

        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props,

                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("edulitsjava@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            message.setSubject("Testing Subject");
            message.setText("Welcome Message");
            Transport.send(message);
            System.out.println("Mail Sent Successfully");
        } catch (AuthenticationFailedException ex) {
            throw new RuntimeException(ex);
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }


        return ResponseEntity.of(Optional.of(
                "Mail was send to " + mailTo));
    }

}
