package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.UserTarget;
import service.UserTargetService;
import service.UserTargetServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "/user-target")
public class UserTargetController {
    UserTargetService userTargetService = new UserTargetServiceImpl();



//    @RequestMapping(
//            value = "/my-target",
//            method = RequestMethod.GET)
    public ResponseEntity getTarget(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                userTargetService.read(id)));
    }
//    @RequestMapping(
//            value = "/my-target/list",
//            method = RequestMethod.GET)
    public ResponseEntity getTargetList() {


        return ResponseEntity.of(Optional.of(userTargetService.readList()));
    }

//    @RequestMapping(
//            value = "/my-target",
//            method = RequestMethod.POST)
    public ResponseEntity postUserTarget() {
        return ResponseEntity.of(Optional.of(
                new UserTarget(1, 1, 2, "OK")));
    }

//    @RequestMapping(
//            value = "/my-target",
//            method = RequestMethod.PUT)
    public ResponseEntity putUserTarget() {
        return ResponseEntity.of(Optional.of(
                new UserTarget(1, 1, 2, "OK")));
    }

//    @RequestMapping(
//            value = "/my-target",
//            method = RequestMethod.DELETE)
    public ResponseEntity deleteTarget(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                new UserTarget(1, 1, 2, "OK")));
    }
}
