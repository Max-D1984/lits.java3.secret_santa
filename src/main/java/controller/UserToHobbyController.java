package controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.UserToHobby;
import service.HobbyService;
import service.HobbyServiceImpl;
import service.UserToHobbyService;
import service.UserToHobbyServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@EnableSwagger2
@RestController
@RequestMapping(value = "/user-to-hobby")

public class UserToHobbyController {
    UserToHobbyService userToHobbyService = new UserToHobbyServiceImpl();

    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/my-user-to-hobby",
            method = RequestMethod.GET)
    public ResponseEntity getUserToHobby(
            @RequestParam Integer id) {
        UserToHobbyService userToHobbyService = new UserToHobbyServiceImpl();
        List<String> hobbyName = new LinkedList<>();
        for (UserToHobby us: userToHobbyService.readListByUserId(id)
        ) {
            HobbyService hobbyService = new HobbyServiceImpl();
            hobbyName.add(hobbyService.readHobby(us.getHobby_id()).getName());
        }
        hobbyName.stream().forEach(y-> System.out.println(y));
        return ResponseEntity.of(Optional.of(List.of(
                hobbyName)));
    }
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/my-user-to-hobby/list",
            method = RequestMethod.GET)
    public ResponseEntity userToHobbyListList() {
        return ResponseEntity.of(Optional.of(List.of(
                userToHobbyService.readList())));
    }

    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/user-to-hobby",
            method = RequestMethod.POST)
    public ResponseEntity postUserToHobbyList(@RequestParam int user_id, int hobby_id){
        UserToHobby newUserToHobby = new UserToHobby(0,user_id,hobby_id,0);
        userToHobbyService.createUserToHobby(newUserToHobby);
        return ResponseEntity.of(Optional.of(
                "Created user to hobby" + newUserToHobby));
    }


//    @RequestMapping(
//            value = "/user-to-hobby",
//            method = RequestMethod.PUT)
//    public ResponseEntity putUserToHobbyList(@RequestParam int id, int newUser_id, int newHobby_id) {
//        userToHobbyService.updateUserToHobby(userToHobbyService.readUserToHobby(id), 1,1);
//        return ResponseEntity.of(Optional.of( "Update user to hobby" + newUser_id + newHobby_id));
//    }

//    @RequestMapping(
//            value = "/user-to-hobby",
//            method = RequestMethod.DELETE)
    public ResponseEntity deleteUserToHobbyList(
            @RequestParam Integer user_id, @RequestParam Integer hobby_id) {
               userToHobbyService.deleteUserToHobby(new UserToHobby(0,user_id,hobby_id, 0));
        return ResponseEntity.of(Optional.of(
                "Deleted user to hobby "));
    }
}
