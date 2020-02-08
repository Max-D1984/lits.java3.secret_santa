package controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.User;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "/wishlist")
public class MyWishListController {
    static {
        System.out.println("here");
    }
    @RequestMapping(
            value = "/my-wishlist",
            method = RequestMethod.GET)
    public ResponseEntity getMyWishList(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                        new User(id,
                                "name",
                                "Hello")));
    }
    @RequestMapping(
            value = "/my-wishlist/list",
            method = RequestMethod.GET)
    public ResponseEntity getMyWishListList() {
        return ResponseEntity.of(Optional.of(List.of(
                new User(3,
                        "name",
                        "Hello"),
                new User(3+3,
                        "name3",
                        "Hello3"))));
    }
    @RequestMapping(
            value = "/my-wishlist",
            method = RequestMethod.POST)
    public ResponseEntity postMyWishList(
            @RequestBody User user) {
        return ResponseEntity.of(Optional.of(
                new User(1,
                        "name",
                        "Hello")));
    }
    @RequestMapping(
            value = "/my-wishlist",
            method = RequestMethod.PUT)
    public ResponseEntity putMyWishList(
            @RequestBody User user) {
        return ResponseEntity.of(Optional.of(
                new User(1,
                        "name",
                        "Hello")));
    }
    @RequestMapping(
            value = "/my-wishlist",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteMyWishList(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                new User(1,
                        "name",
                        "Hello")));
    }
}