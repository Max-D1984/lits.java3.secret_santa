package controller;

import model.MyWishListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserToPresentService;
import service.UserToPresentServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "/wishlist")
public class MyWishListController {
    @RequestMapping(
            value = "/my-wishlist/list",
            method = RequestMethod.GET)
    public ResponseEntity getMyWishListList(@RequestParam Integer targetwishlist) {
        UserToPresentService userToPresentDal = new UserToPresentServiceImpl();
        List<MyWishListResponse> presentForUserId = userToPresentDal.readPresentListById(targetwishlist);
        return ResponseEntity.of(Optional.of(presentForUserId));
    }
}