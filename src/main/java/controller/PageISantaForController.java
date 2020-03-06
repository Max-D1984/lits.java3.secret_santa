package controller;

import dal.UserTargetDalImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import model.TargetUserIdAndCompanyId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.*;
import java.util.List;
import java.util.Optional;

@SwaggerDefinition
@RestController
@RequestMapping (value = "/logged-user")


public class PageISantaForController {
   public static int LOGGEDUSER = 1;
    @ApiOperation("Return information about all your targets (you santa for)")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
     @RequestMapping(
            value = "/i-santa-for",
            method = RequestMethod.GET)
    public ResponseEntity putMyWishList(
           ) {
        UserToCompanyService userToCompanyService = new UserToCompanyServiceImpl();
        UserTargetService userTargetService = new UserTargetServiceImpl();

        List<Integer> listComp = userToCompanyService.getCompanysByUserId(LOGGEDUSER);

        List<Integer> listTarg = userTargetService.getTargetForUserById(LOGGEDUSER);
        UserService userService = new UserServiceImpl();
        CompanyService companyService= new CompanyServiceImpl();
        List<TargetUserIdAndCompanyId> list = userService.getAllTargetsForUser(listComp, listTarg);

        for (TargetUserIdAndCompanyId target: list) {
            target.setUserName(userService.readUser(target.getUser_id()).getUserName());
            target.setCompanyName(companyService.readCompany(target.getCompany_id()).getCompanyName());

        }

        return ResponseEntity.of(Optional.of(List.of(list)));
    }

}
