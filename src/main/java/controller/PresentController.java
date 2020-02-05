package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Present;
import pojo.User;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/present")

public class PresentController {
    @RequestMapping(
            value = "/present",
            method = RequestMethod.GET)
    public ResponseEntity getPresent(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                new Present(id,
                        "SomeNameOfPresent",
                        "http://")));
    }

    @RequestMapping(
            value = "/present/list",
            method = RequestMethod.GET)
    public ResponseEntity presentListList() {
        return ResponseEntity.of(Optional.of(List.of(
                new Present(2,
                        "somePresentName",
                        "http://..."),
                new Present (2 + 2,
                        "somePresentName3",
                        "http://..3"))));
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
