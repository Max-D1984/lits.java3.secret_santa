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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Present;
import pojo.User;
import service.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
@EnableSwagger2
@RestController
@RequestMapping(value = "/user")

public class UserController {

    UserService us = new UserServiceImpl();
    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET)
    public ResponseEntity getUser(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
               us.readUser(id)));
    }

    @RequestMapping(
            value = "/user/list",
            method = RequestMethod.GET)
    public ResponseEntity userListList() {
        return ResponseEntity.of(Optional.of(List.of(
                new User(2,
                        "someUserName",
                        "http://..."),
                new User (2 + 2,
                        "someUserName3",
                        "http://..3"))));
    }

    @RequestMapping(
            value = "/user",
            method = RequestMethod.POST)
    public ResponseEntity postUserList(
            @RequestBody User user) {
        return ResponseEntity.of(Optional.of(
                new User(1,
                        "SomeUserName",
                        "http://Hello")));
    }

    @RequestMapping(
            value = "/user",
            method = RequestMethod.PUT)
    public ResponseEntity putUserList(
            @RequestBody User present) {
        return ResponseEntity.of(Optional.of(
                new User(2,
                        "SomeUserName",
                        "Hello")));
    }

    @RequestMapping(
            value = "/user",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteUserList(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                new User(1,
                        "SomeUserName",
                        "http://Hellooooo")));
    }
}
