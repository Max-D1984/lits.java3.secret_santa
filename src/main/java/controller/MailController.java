package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pojo.Company;
import pojo.User;
import pojo.UserToCompany;
import service.CompanyService;
import service.UserTargetService;
import service.UserToCompanyService;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
public class MailController {

    private int loggedInUserId = 0;

    public CompanyService getCompanyService() {
        return companyService;
    }

    @Autowired
    private CompanyService companyService ;

    public UserToCompanyService getUserToCompanyService() {
        return userToCompanyService;
    }
    @Autowired
    private UserToCompanyService userToCompanyService;

    public UserTargetService getUserTargetService() {
        return userTargetService;
    }

    @Autowired
    private UserTargetService userTargetService;

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


    @RequestMapping(
            value = "/mail-to-user",
            method = RequestMethod.GET)
    public ResponseEntity getUserToCompany(
            @RequestParam String mailTo,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Integer companyId,
            HttpServletRequest request,
            HttpServletResponse response) {

        List<UserToCompany> currentUserList = userToCompanyService.readListByCompanyId(companyId);
        StringBuilder urlToCompany = new StringBuilder();

        HttpRequest httpRequest = new ServletServerHttpRequest(request);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpRequest(httpRequest).build();
        String scheme = uriComponents.getScheme();
        String host = uriComponents.getHost();
        if (host.equals("localhost")) {

            urlToCompany.append("http://localhost:8080/secret-santa/user/userlist?id=" + companyId);
        } else {
            urlToCompany.append("https://lits-java3-secret-santa.herokuapp.com/user/userlist?id=" + companyId);
        }
        if (currentUserList.get(loggedInUserId).getRole().equals("admin")) {
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

                Company company = companyService.readCompany(companyId);
                message.setText("Dear " + mailTo.substring(0, mailTo.lastIndexOf("@")) + ",\n"
                        + "\n"
                        + "I want to invite you to participate in a Secret Santa game to my company " + company.getCompanyName()
                        + "\n"
                        + "If you want to confirm, please, follow this link " + urlToCompany);
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
        return ResponseEntity.of(Optional.of(
                "Mail sending was failed"
        ));
    }


    @RequestMapping(
            value = "/define-santas",
            method = RequestMethod.GET)
    public ResponseEntity generateUsersAndTargetsList(
            @RequestParam String mailTo,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Integer companyId) {

        List<User> currentUserList = userToCompanyService.readUserByCompanyId(companyId);
        Map<Integer, Integer> usersId = userTargetService.generateMapOfUsers(currentUserList);
        StringBuilder userList = new StringBuilder();
        Set mapSet = usersId.entrySet();
//        Iterator iterator = mapSet.iterator();
//        while(iterator.hasNext()) {
//            Map.Entry entry = (Map.Entry) iterator.next();
//
//        }
        int userId = 0;
        int targetId = 0;
        for (Map.Entry<Integer, Integer> entry : usersId.entrySet()) {
            userId = entry.getKey();
            targetId = entry.getValue();
            for (User user : currentUserList) {
                if (user.getId() == userId) {
                    for(User target: currentUserList){
                        if(target.getId() == targetId){
                            userList.append("User " + user.getUserName() + " is Santa for " + target.getUserName() + "; ");
                        }
                    }

                }
            }
        }
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
            message.setSubject("List");
            message.setText(String.valueOf(userList));
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
