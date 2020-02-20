package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Present;
import service.PresentService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
@EnableSwagger2
@RestController
@RequestMapping(value = "/present")

public class PresentController {
    //    private static Long userId =

    @Autowired
    private PresentService pres;
    public PresentService getPres() {return pres;
    }
    @RequestMapping(
            value = "/my-present",
            method = RequestMethod.GET)
    public ResponseEntity getPresent(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
              pres.readPresent(id)));
    }

    @RequestMapping(
            value = "/my-present/list",
            method = RequestMethod.GET)
    public ResponseEntity presentListList() {
        return ResponseEntity.of(Optional.of(List.of(
                pres.readList())));
    }

    @RequestMapping(
            value = "/present",
            method = RequestMethod.POST)
    public ResponseEntity postPresentList(@RequestParam String name, String url){
        Present newPresent = new Present(1,name, url);
        pres.createPresent(newPresent);
        return ResponseEntity.of(Optional.of(
                "Created present" + newPresent));
    }


    @RequestMapping(
            value = "/present",
            method = RequestMethod.PUT)
    public ResponseEntity putPresentList(@RequestParam int id, String newName, String newUrl) {
        pres.updatePresent(pres.readPresent(id), newName,newUrl);
        return ResponseEntity.of(Optional.of( "Update present" + newName + newUrl));
    }

    @RequestMapping(
            value = "/present",
            method = RequestMethod.DELETE)
    public ResponseEntity deletePresentList(
            @RequestParam Integer id) {
        Present deletedPresent = pres.readPresent(1);
        pres.deletePresent(deletedPresent);
        return ResponseEntity.of(Optional.of(
                "Deleted present "+ deletedPresent));
    }
}
