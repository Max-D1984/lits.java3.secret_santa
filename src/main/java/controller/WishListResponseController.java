package controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/my-wish-list",
            method = RequestMethod.GET)
    public ResponseEntity getMyWishListList(@RequestParam Integer loggedUserId) {
        return ResponseEntity.of(Optional.of(wishListService.loggeUserWishListResponse(loggedUserId)));
    }
    @ApiOperation("Метод показує нам як зареєстрованому юзеру wishList вибраного нами таргета. \n " +
            "Однак, якщо з цього списку хтось вже вибрав подарунок з іншої компанії, то для нас вибрані подарунки іншими юзерами не відображаються \n" +
            "Якщо ми вибрали якийсь подарунок, то буде передано в списку статус вибрано")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/my-target-wish-list",
            method = RequestMethod.GET)
    public ResponseEntity getMyTargetWishListList(@RequestParam Integer targetUserId, @RequestParam Integer loggedUserId) {
        return ResponseEntity.of(Optional.of(wishListService.targetUserWishListResponse(targetUserId, loggedUserId)));
    }

    @RequestMapping(
            value = "/my-wish-list",
            method = RequestMethod.PUT)
    public ResponseEntity putPresentToMyWishListList(@RequestParam String presentName, @RequestParam String presentURL, @RequestParam Integer loggedUserId) {
        wishListService.addPresentToWishlist(presentName,presentURL,loggedUserId);
        return ResponseEntity.of(Optional.of(getMyWishListList(loggedUserId)));
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