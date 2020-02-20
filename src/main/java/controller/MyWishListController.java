package controller;

import model.MyWishListResponse;
import model.Present;
import model.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import model.Hobby;
import pojo.UserToHobby;


import pojo.UserToPresent;
import service.HobbyService;
import service.HobbyServiceImpl;
import service.UserToHobbyService;
import service.UserToPresentService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableSwagger2
@RestController
@RequestMapping(value = "/wish-list")
public class MyWishListController {

    private int loggedInUserId = 6;


    public UserToHobbyService getUserToHobbyService() {
        return userToHobbyService;
    }
    @Autowired
    private UserToHobbyService userToHobbyService;

    public UserToPresentService getUserToPresentService() {
        return userToPresentService;
    }

    @Autowired
     private UserToPresentService userToPresentService;


    @RequestMapping(
            value = "/my-wish-list/list",
            method = RequestMethod.GET)
    public ResponseEntity getMyWishListList() {
        return getMyTargetWishListList(loggedInUserId);
    }


    @RequestMapping(
            value = "/my-target-wish-list",
            method = RequestMethod.GET)
    public ResponseEntity getMyTargetWishListList(@RequestParam Integer targetwishlist) {

        List<MyWishListResponse> presentForUserId = userToPresentService.readPresentListById(targetwishlist);

        List<Present> presentList = presentForUserId.stream()
                .map(presentFromDal -> new Present(presentFromDal.getName(), presentFromDal.getUrl()))
                .collect(Collectors.toList());


        WishList wishList = new WishList();

        HobbyService hobbyService = new HobbyServiceImpl();
        List<pojo.UserToHobby> userToHobbies = userToHobbyService.readListByUserId(targetwishlist);

        List<pojo.Hobby> hobbies = new ArrayList<>();
        for (UserToHobby currentUserToHobbies : userToHobbies) {
            pojo.Hobby hobby = hobbyService.readHobby(currentUserToHobbies.getHobby_id());
            hobbies.add(hobby);

        }

        List<Hobby> hobbyList = hobbies.stream()
                .map(hobbyFromDal -> new Hobby(hobbyFromDal.getId(), hobbyFromDal.getName()))
                .collect(Collectors.toList());

        wishList.setHobbyList(hobbyList);
        wishList.setPresentList(presentList);

        return ResponseEntity.of(Optional.of(wishList));
    }
}