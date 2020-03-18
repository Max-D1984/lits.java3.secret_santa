//package controler;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import pojo.User;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(value = "/wishlist")
//
//public class MyWishListController {
//    @RequestMapping(
//            value = "/my-wishlist",
//            method = RequestMethod.GET)
//    public ResponseEntity getMyWishList(
//            @RequestParam Integer id) {
//        return ResponseEntity.of(Optional.of(
//                new User(id,
//                        "name",
//                        "Hello")));
//    }
//    @RequestMapping(
//            value = "/my-wishlist/list",
//            method = RequestMethod.GET)
//    public ResponseEntity getMyWishListList() {
//        return ResponseEntity.of(Optional.of(List.of(
//                new User(3,
//                        "name",
//                        "Hello"),
//                new User(3+3,
//                        "name3",
//                        "Hello3"))));
//    }
//    @RequestMapping(
//            value = "/my-wishlist",
//            method = RequestMethod.POST)
//    public ResponseEntity postMyWishList(
//            @RequestBody User user) {
//        return ResponseEntity.of(Optional.of(
//                new User(1,
//                        "name",
//                        "Hello")));
//    }
//    @RequestMapping(
//            value = "/my-wishlist",
//            method = RequestMethod.PUT)
//    public ResponseEntity putMyWishList(
//            @RequestBody User user) {
//        return ResponseEntity.of(Optional.of(
//                new User(1,
//                        "name",
//                        "Hello")));
//    }
//    @RequestMapping(
//            value = "/my-wishlist",
//            method = RequestMethod.DELETE)
//    public ResponseEntity deleteMyWishList(
//            @RequestParam Integer id) {
//        return ResponseEntity.of(Optional.of(
//                new User(1,
//                        "name",
//                        "Hello")));
//    }
//}

package controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Present;
import pojo.User;
import pojo.UserToCompany;
import service.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@EnableSwagger2
@RestController
@RequestMapping(value = "/user")

public class UserController {

    UserService us = new UserServiceImpl();
    UserToCompanyService userToCompanyService = new UserToCompanyServiceImpl();

    //    @RequestMapping(
//            value = "/user",
//            method = RequestMethod.GET)
    public ResponseEntity getUser(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                us.readUser(id)));
    }

    //    @RequestMapping(
//            value = "/user/list",
//            method = RequestMethod.GET)
    public ResponseEntity userListList() {
        return ResponseEntity.of(Optional.of(List.of(
                new User(2,
                        "someUserName", "someEmail", "somePass"),
                new User(2 + 2,
                        "someUserName3", "someEmail", "somePass"))));
    }

    //    @RequestMapping(
//            value = "/user",
//            method = RequestMethod.POST)
    public ResponseEntity postUserList(
            @RequestBody User user) {
        return ResponseEntity.of(Optional.of(
                new User(1,
                        "SomeUserName", "someEmail", "somePass")));
    }

    //    @RequestMapping(
//            value = "/user",
//            method = RequestMethod.PUT)
    public ResponseEntity putUserList(
            @RequestBody User present) {
        return ResponseEntity.of(Optional.of(
                new User(2,
                        "SomeUserName", "someEmail", "somePass")));
    }

    //    @RequestMapping(
//            value = "/user",
//            method = RequestMethod.DELETE)
    public ResponseEntity deleteUserList(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                new User(1,
                        "SomeUserName", "someEmail", "somePass")));
    }

    @ApiOperation("Return name of users in exact company")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/userlist",
            method = RequestMethod.GET)
    public ResponseEntity getUsersByCompanyId(
            @RequestParam Integer id) {
        List<String> userName = new LinkedList<>();
        List<UserResponse> userList = userToCompanyService.readUserByCompanyId(id);
        for (UserResponse user : userList) {
            userName.add(user.getName());
        }
        return ResponseEntity.of(Optional.of(
                userName));
    }
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/save-user",
            method = RequestMethod.PUT)
    public ResponseEntity putUser(
            @RequestParam String userName, @RequestParam String email, @RequestParam String passWord) {
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassWord(passWord);
        UserRegistrationService userRegistrationService = new UserRegistrationServiceImpl();
        userRegistrationService.save(user);
        sendEmail(email,"litscvjava3@gmail.com" ,"MZOAM123456789","You sre is registred");
        return ResponseEntity.of(Optional.of(""));

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
