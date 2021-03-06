package controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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

    private WishListService wishListService = new WishListServiceImp();
    private UserToPresentService userToPresentService = new UserToPresentServiceImpl();

//    @Autowired
//    public WishListService wishListService;
//
//    public WishListService getWishListService() {
//        return wishListService;
//    }

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

    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/my-wish-list",
            method = RequestMethod.PUT)
    public ResponseEntity putPresentToMyWishListList(@RequestParam Integer loggedUserId, @RequestParam Integer presentId) {
        wishListService.addPresentToWishlist(loggedUserId,presentId);
        return ResponseEntity.of(Optional.of(getMyWishListList(loggedUserId)));
    }

    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/my-wish-list",
            method = RequestMethod.DELETE)
    public ResponseEntity deletePresentToMyWishListList(@RequestParam Integer loggedUserId, @RequestParam Integer presentId) {
        wishListService.deletePresentFromWishlist(loggedUserId, presentId);
        return ResponseEntity.of(Optional.of(getMyWishListList(loggedUserId)));
    }

    @ApiOperation("Метод який на вхід отримує id залогіненого юзера, id юзера для якого зологінений буде сантою, id подарунку, та параметр boolean\n"
            + "в звлежності від якого подарунок буде вибраний(надіслано id залогіненого юзера) або не вибраний (відправлення 0)")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/check-present-by-santa",
            method = RequestMethod.POST)
    public ResponseEntity checkPresent(
            @RequestParam Integer targetId, Integer presentId, Integer santaId, boolean check) {
        Integer id;
        if(check){
            id= santaId;
        }else{
            id=0;
        }
        userToPresentService.setSantaIdInUserToPresent(targetId,presentId,id);
        return ResponseEntity.of(Optional.of(List.of(userToPresentService.readPresentListById(id))));

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