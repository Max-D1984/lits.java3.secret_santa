package controller;

import dal.UserToPresentDal;
import dal.UserToPresentDalImpl;
import model.MyWishListResponse;
//import model.Present;
//import model.WishList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserToPresentService;
import service.UserToPresentServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableSwagger2
@RestController
@RequestMapping(value = "/wish-list")
public class MyWishListController {

    private int loggedInUserId = 8;

    @RequestMapping(
            value = "/my-wish-list/list",
            method = RequestMethod.GET)
    public ResponseEntity getMyWishListList() {
        UserToPresentService userToPresentDal = new UserToPresentServiceImpl();
        List<MyWishListResponse> presentForUserId = userToPresentDal.readPresentListById(loggedInUserId);
        return ResponseEntity.of(Optional.of(presentForUserId));
    }


//    @RequestMapping(
//            value = "/my-target-wish-list",
//            method = RequestMethod.GET)
//    public ResponseEntity getMyTargetWishListList(@RequestParam Integer targetwishlist) {
//        UserToPresentService userToPresentService = new UserToPresentServiceImpl();
//        List<MyWishListResponse> presentForUserId = userToPresentService.readPresentListById(targetwishlist);
//
//        List<Present> presentList = presentForUserId.stream()
//                .map(presentFromDal -> new Present(presentFromDal.getName(), presentFromDal.getUrl()))
//                .collect(Collectors.toList());
//
//
//        WishList wishList = new WishList();
//        wishList.setHobbyList(null);
//        wishList.setPresentList(presentList);
//
//        return ResponseEntity.of(Optional.of(wishList));
//    }
}