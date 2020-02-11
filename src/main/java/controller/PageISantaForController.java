package controller;

import io.swagger.annotations.SwaggerDefinition;
import model.ISantaForListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SwaggerDefinition
@RestController
@RequestMapping (value = "/logged-user")


public class PageISantaForController {
    int loggedUserId = 1;


    @RequestMapping(
            value = "/i-santa-for",
            method = RequestMethod.GET)
    public ResponseEntity putMyWishList(
           ) {
        return ResponseEntity.of(Optional.of(List.of(
                new ISantaForListResponse("UserName", "Company",
                        "Rule", "WishList", "Chosen Present", Boolean.TRUE),
                new ISantaForListResponse("UserName2", "Company2", "Rule2",
                "WishList2", "Chosen Present2", Boolean.FALSE
        ))));
    }

}
