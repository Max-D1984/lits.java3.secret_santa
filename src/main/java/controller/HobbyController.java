package controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Hobby;
import service.HobbyService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
@EnableSwagger2
@RestController
@RequestMapping(value = "/hobby")

public class HobbyController {
   @Autowired
    private HobbyService hobbyService;
    public HobbyService getHobbyService() {
        return hobbyService;
    }
    @ApiOperation("REad hobby by hobby id")
    @RequestMapping(
            value = "/my-hobby",
            method = RequestMethod.GET)
    public ResponseEntity getHobby(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                hobbyService.readHobby(id)));
    }
    @ApiOperation("Read all hobby")
    @RequestMapping(
            value = "/my-hobby/list",
            method = RequestMethod.GET)
    public ResponseEntity hobbyListList() {
        return ResponseEntity.of(Optional.of(List.of(
                hobbyService.readList())));
    }
    @ApiOperation("Create hobby")
    @RequestMapping(
            value = "/hobby",
            method = RequestMethod.POST)
    public ResponseEntity postHobbyList(@RequestParam String name){
        Hobby newHobby = new Hobby(1, "nameOfHobby");
        hobbyService.createHobby(newHobby);
        return ResponseEntity.of(Optional.of(
                "Created hobby" + newHobby));
    }


//    @RequestMapping(
//            value = "/hobby",
//            method = RequestMethod.PUT)
    public ResponseEntity putHobbyList(@RequestParam int id, String newName) {
        hobbyService.updateHobby(hobbyService.readHobby(id), newName);
        return ResponseEntity.of(Optional.of( "Update hobby" + newName));
    }
    @ApiOperation("Delete hobby")
    @RequestMapping(
            value = "/hobby",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteHobbyList(
            @RequestParam Integer id) {
        Hobby deletedHobby = hobbyService.readHobby(1);
        hobbyService.deleteHobby(deletedHobby);
        return ResponseEntity.of(Optional.of(
                "Deleted hobby "+ deletedHobby));
    }
}
