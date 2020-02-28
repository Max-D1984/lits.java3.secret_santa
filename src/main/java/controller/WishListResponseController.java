package controller;

import model.LoggedUserHobbyResponse;
import model.MyWishListResponse;
import model.LoggedUserPresentResponse;
import model.LoggedUserWishListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.UserToHobby;


import service.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableSwagger2
@RestController
@RequestMapping(value = "/wish-list")
public class WishListResponseController {

    private int loggedInUserId = 6;

    @Autowired
    public WishListService wishListService;

    public WishListService getWishListService() {
        return wishListService;
    }

    @RequestMapping(
            value = "/my-wish-list",
            method = RequestMethod.GET)
    public ResponseEntity getMyWishListList(@RequestParam Integer loggedUserId) {
        return ResponseEntity.of(Optional.of(wishListService.loggeUserWishListResponse(loggedUserId)));
    }


    @RequestMapping(
            value = "/my-target-wish-list",
            method = RequestMethod.GET)
    public ResponseEntity getMyTargetWishListList(@RequestParam Integer targetUserId) {
        return ResponseEntity.of(Optional.of(wishListService.targetUserWishListResponse(targetUserId)));
    }
}


//    List<MyWishListResponse> presentForUserId = userToPresentService.readPresentListById(targetUserId);
//
//    List<LoggedUserPresentResponse> loggedUserPresentResponseList = presentForUserId.stream()
//            .map(presentFromDal -> new LoggedUserPresentResponse(presentFromDal.getName(), presentFromDal.getUrl()))
//            .collect(Collectors.toList());
//
//
//    LoggedUserWishListResponse loggedUserWishListResponse = new LoggedUserWishListResponse();
//
//    HobbyService hobbyService = new HobbyServiceImpl();
//    List<pojo.UserToHobby> userToHobbies = userToHobbyService.readListByUserId(targetUserId);
//
//    List<pojo.Hobby> hobbies = new ArrayList<>();
//        for (UserToHobby currentUserToHobbies : userToHobbies) {
//                pojo.Hobby hobby = hobbyService.readHobby(currentUserToHobbies.getHobby_id());
//                hobbies.add(hobby);
//
//                }
//
//                List<LoggedUserHobbyResponse> loggedUserHobbyResponseList = hobbies.stream()
//        .map(hobbyFromDal -> new LoggedUserHobbyResponse(hobbyFromDal.getId(), hobbyFromDal.getName()))
//        .collect(Collectors.toList());
//
//        loggedUserWishListResponse.setLoggedUserHobbyResponseList(loggedUserHobbyResponseList);
//        loggedUserWishListResponse.setLoggedUserPresentResponseList(loggedUserPresentResponseList);