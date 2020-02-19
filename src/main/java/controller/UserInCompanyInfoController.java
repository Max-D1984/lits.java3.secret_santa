package controller;

import dal.*;
import model.UserAndUserTargetId;
import model.UserInCompanyInformationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.UserTargetService;
import service.UserTargetServiceImpl;
import service.UserToCompanyService;
import service.UserToCompanyServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@EnableSwagger2
    @RestController
    @RequestMapping(value = "/user-in-company-info")
    public class UserInCompanyInfoController {

        @RequestMapping(
                value = "/my-user-in-company-info/list",
                method = RequestMethod.GET)
        public ResponseEntity getCompanyInfoListList() {
            UserToCompanyService userToCompanyService = new UserToCompanyServiceImpl();
            List<Integer> userIdOfCompany = userToCompanyService.getUsersOfCompany(1);
            UserTargetService userTargetService = new UserTargetServiceImpl();
            List<UserAndUserTargetId> userAndUserTargetIdList = new LinkedList<>();
            for (Integer id:userIdOfCompany
            ) {
                userAndUserTargetIdList.addAll(userTargetService.getTargetForUserInCompany(id, userIdOfCompany));
            }

            userAndUserTargetIdList.stream().forEach(y-> System.out.println(y));

            for (UserAndUserTargetId obj:userAndUserTargetIdList
            ) {
                UserDal userDal = new UserDalImpl();
                obj.setUserName(userDal.read(obj.getUser_id()).getUserName());
                obj.setTargetName(userDal.read(obj.getTarget_id()).getUserName());
            }
            return ResponseEntity.of(Optional.of(List.of(
                     userAndUserTargetIdList)));
        }

//    }
}
