package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Hobby;
import pojo.Present;
import service.HobbyService;
import service.HobbyServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
@EnableSwagger2
@RestController
@RequestMapping(value = "/hobby")

public class HobbyController {
    HobbyService hob = new HobbyServiceImpl();
    @RequestMapping(
            value = "/my-hobby",
            method = RequestMethod.GET)
    public ResponseEntity getHobby(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                hob.readHobby(id)));
    }

    @RequestMapping(
            value = "/my-hobby/list",
            method = RequestMethod.GET)
    public ResponseEntity hobbyListList() {
        return ResponseEntity.of(Optional.of(List.of(
                hob.readList())));
    }

    @RequestMapping(
            value = "/hobby",
            method = RequestMethod.POST)
    public ResponseEntity postHobbyList(@RequestParam String name){
        Hobby newHobby = new Hobby(1, "nameOfHobby");
        hob.createHobby(newHobby);
        return ResponseEntity.of(Optional.of(
                "Created hobby" + newHobby));
    }


    @RequestMapping(
            value = "/hobby",
            method = RequestMethod.PUT)
    public ResponseEntity putHobbyList(@RequestParam int id, String newName) {
        hob.updateHobby(hob.readHobby(id), newName);
        return ResponseEntity.of(Optional.of( "Update hobby" + newName));
    }

    @RequestMapping(
            value = "/hobby",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteHobbyList(
            @RequestParam Integer id) {
        Hobby deletedHobby = hob.readHobby(1);
        hob.deleteHobby(deletedHobby);
        return ResponseEntity.of(Optional.of(
                "Deleted hobby "+ deletedHobby));
    }
}
