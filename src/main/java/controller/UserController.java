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

import configuration.JwtTokenUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import model.LoginRequest;
import model.LoginResponse;
import model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pojo.User;
import service.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
//    )
    @RequestMapping(
            value = "/save-user",
            method = RequestMethod.PUT)
    public ResponseEntity putUser(
            @RequestParam String userName, @RequestParam String email, @RequestParam String passWord) {
        MailController sendEmailRegistr = new MailController();
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassWord(passWord);
        UserRegistrationService userRegistrationService = new UserRegistrationServiceImpl();
        userRegistrationService.save(user);
        sendEmailRegistr.sendEmail(email,"litscvjava3@gmail.com" ,"MZOAM123456789","You sre is registred");
        return ResponseEntity.of(Optional.of(""));

    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    private UserDetailsService userDetailsService = new UserDetailsServiceImpl();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody LoginRequest authenticationRequest) throws Exception {




        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDetailsService userDetailsService = new UserDetailsServiceImpl();
        final UserDetails userDetails = userDetailsService
                .loadUserByUserName(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
