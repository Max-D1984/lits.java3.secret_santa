package controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import model.LoggedUserHobbyResponse;
import model.LoggedUserPresentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.UserToPresentService;
import service.UserToPresentServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableSwagger2
@RestController
@RequestMapping(value = "/user-to-present")
public class UserToPresentController {
    UserToPresentService userToPresentService = new UserToPresentServiceImpl();

    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/set-user-to-present",
            method = RequestMethod.POST)
    public ResponseEntity getUserToHobby(
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

