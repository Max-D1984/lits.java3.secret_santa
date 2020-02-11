package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.UserToHobby;
import service.UserToHobbyService;
import service.UserToHobbyServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
@EnableSwagger2
@RestController
@RequestMapping(value = "/user-to-hobby")

public class UserToHobbyController {
    UserToHobbyService userToHobbyService = new UserToHobbyServiceImpl();
    @RequestMapping(
            value = "/my-user-to-hobby",
            method = RequestMethod.GET)
    public ResponseEntity getUserToHobby(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                userToHobbyService.readUserToHobby(id)));
    }

    @RequestMapping(
            value = "/my-user-to-hobby/list",
            method = RequestMethod.GET)
    public ResponseEntity userToHobbyListList() {
        return ResponseEntity.of(Optional.of(List.of(
                userToHobbyService.readList())));
    }

    @RequestMapping(
            value = "/user-to-hobby",
            method = RequestMethod.POST)
    public ResponseEntity postUserToHobbyList(@RequestParam int user_id, int hobby_id){
        UserToHobby newUserToHobby = new UserToHobby(1,1,1);
        userToHobbyService.createUserToHobby(newUserToHobby);
        return ResponseEntity.of(Optional.of(
                "Created user to hobby" + newUserToHobby));
    }


    @RequestMapping(
            value = "/user-to-hobby",
            method = RequestMethod.PUT)
    public ResponseEntity putUserToHobbyList(@RequestParam int id, int newUser_id, int newHobby_id) {
        userToHobbyService.updateUserToHobby(userToHobbyService.readUserToHobby(id), 1,1);
        return ResponseEntity.of(Optional.of( "Update user to hobby" + newUser_id + newHobby_id));
    }

    @RequestMapping(
            value = "/user-to-hobby",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteUserToHobbyList(
            @RequestParam Integer id) {
        UserToHobby deletedUserToHobby = userToHobbyService.readUserToHobby(1);
        userToHobbyService.deleteUserToHobby(deletedUserToHobby);
        return ResponseEntity.of(Optional.of(
                "Deleted user to hobby "+ deletedUserToHobby));
    }
}
