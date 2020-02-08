package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Present;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
@EnableSwagger2
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
            value = "/present",
            method = RequestMethod.POST)
    public ResponseEntity postPresentList(
            @RequestBody Present present) {
        return ResponseEntity.of(Optional.of(
                new Present(1,
                        "SomePresentName",
                        "http://Hello")));
    }

    @RequestMapping(
            value = "/present",
            method = RequestMethod.PUT)
    public ResponseEntity putPresentList(
            @RequestBody Present present) {
        return ResponseEntity.of(Optional.of(
                new Present(1,
                        "SomePresentName",
                        "Hello")));
    }

    @RequestMapping(
            value = "/present",
            method = RequestMethod.DELETE)
    public ResponseEntity deletePresentList(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                new Present(1,
                        "SomePresentName",
                        "http://Hellooooo")));
    }
}
