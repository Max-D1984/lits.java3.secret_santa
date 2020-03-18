package controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import model.UserResponse;
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

    @Autowired
    private CompanyService companyService ;
    @Autowired
    private UserToCompanyService userToCompanyService;
    @Autowired
    private UserTargetService userTargetService;

    public CompanyService getCompanyService() {
        return companyService;
    }

    public UserToCompanyService getUserToCompanyService() {
        return userToCompanyService;
    }

    public UserTargetService getUserTargetService() {
        return userTargetService;
    }
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/mail",
            method = RequestMethod.GET)
    public ResponseEntity getUser(
            @RequestParam String mailTo
) {

        Properties props = new Properties();

        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props,

                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(System.getenv("lits_email_login"), System.getenv("lits_email_password"));
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

    @ApiOperation("Send email to user with invitation to company")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
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
        String host = uriComponents.getHost();
        Company company = companyService.readCompany(companyId);
        String subject = "Testing Subject";
        if (host.equals("localhost")) {

            urlToCompany.append("http://localhost:8080/secret-santa/user/userlist?id=" + companyId);
        } else {
            urlToCompany.append("https://lits-java3-secret-santa.herokuapp.com/user/userlist?id=" + companyId);
        }
        String mailText = "Dear " + mailTo.substring(0, mailTo.lastIndexOf("@")) + ",\n"
                + "\n"
                + "I want to invite you to participate in a Secret Santa game to my company " + company.getCompanyName()
                + "\n"
                + "If you want to confirm, please, follow this link " + urlToCompany;
        if (sendEmail(mailTo, username, password, currentUserList, subject, mailText)) {
            return ResponseEntity.of(Optional.of("Mail was send to " + mailTo));
        }
        return ResponseEntity.of(Optional.of("Mail sending was failed"));
    }

    public boolean sendEmail(String mailTo, String username, String password, List<UserToCompany> currentUserList, String subject, String mailText) {
        if (currentUserList.get(loggedInUserId).getRole().equals("admin")) {
            Properties props = new Properties();
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.ssl.enable", true);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");

            Session session = Session.getInstance(props,

                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("edulitsjava@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(mailTo));
                message.setSubject(subject);


                message.setText(mailText);
                Transport.send(message);
                System.out.println("Mail Sent Successfully");
            } catch (AuthenticationFailedException ex) {
                throw new RuntimeException(ex);
            } catch (MessagingException ex) {
                throw new RuntimeException(ex);
            }
            return true;
        }
        return false;
    }

    @ApiOperation("Generate user->target of user and send email with list of couple user->target")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/define-santas",
            method = RequestMethod.GET)
    public ResponseEntity generateUsersAndTargetsList(
            @RequestParam String mailTo,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Integer companyId) {

        List<UserResponse> currentUserList = userToCompanyService.readUserByCompanyId(companyId);
        Map<Integer, Integer> usersId = userTargetService.generateMapOfUsers(currentUserList);
        StringBuilder userList = new StringBuilder();
        int userId = 0;
        int targetId = 0;
        for (Map.Entry<Integer, Integer> entry : usersId.entrySet()) {
            userId = entry.getKey();
            targetId = entry.getValue();
            for (UserResponse user : currentUserList) {
                if (user.getId() == userId) {
                    for(UserResponse target: currentUserList){
                        if(target.getId() == targetId){
                            userList.append("User " + user.getName() + " is Santa for " + target.getName() + "; ");
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


    public boolean sendEmail (String mailTo, String username, String password, String mailText) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,

                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("edulitsjava@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));

            message.setText(mailText);
            Transport.send(message);
            System.out.println("Mail Sent Successfully");
        } catch (AuthenticationFailedException ex) {
            throw new RuntimeException(ex);
        } catch (MessagingException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
}
