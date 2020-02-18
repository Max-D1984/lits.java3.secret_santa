package controller;

import dal.UserDalImpl;
import dal.UserTargetDalImpl;
import dal.UserToCompanyDal;
import dal.UserToCompanyDalImpl;
import io.swagger.annotations.SwaggerDefinition;
import model.ISantaForListResponse;
import model.TargetUserIdAndCompanyId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserTargetService;
import service.UserTargetServiceImpl;
import service.UserToCompanyService;
import service.UserToCompanyServiceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SwaggerDefinition
@RestController
@RequestMapping (value = "/logged-user")


public class PageISantaForController {
   public static int LOGGEDUSER = 1;

    UserTargetDalImpl sss = new UserTargetDalImpl();

    @RequestMapping(
            value = "/i-santa-for",
            method = RequestMethod.GET)
    public ResponseEntity putMyWishList(
           ) {
        UserToCompanyService userToCompanyService = new UserToCompanyServiceImpl();
        UserTargetService userTargetService = new UserTargetServiceImpl();

        List<Integer> listComp = userToCompanyService.getCompanysByUserId(LOGGEDUSER);

        List<Integer> listTarg = userTargetService.getTargetForUserById(LOGGEDUSER);
        UserDalImpl userDal = new UserDalImpl();
        List<TargetUserIdAndCompanyId> list = userDal.getAllTargetsForUser(listComp, listTarg);




        return ResponseEntity.of(Optional.of(List.of(
                list
        )));
    }

}
