package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pojo.UserToCompany;
import service.UserToCompanyService;
import service.UserToCompanyServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@EnableSwagger2
@RestController
@RequestMapping(value = "/")
public class AdminController {

    UserToCompanyService userToCompanyService = new UserToCompanyServiceImpl();
    private int loggedInUserId = 1;

    @RequestMapping(
            value = "/set-admin",
            method = RequestMethod.PUT)
    public ResponseEntity setAdmin(
            @RequestParam Integer company_id,
            @RequestParam Integer user_id) {

        List<UserToCompany> userToCompanyList = userToCompanyService.readListByCompanyId(company_id);
//        for (UserToCompany users : userToCompanyList) {
//            if (loggedInUserId == users.getUser_id() && users.getRole().equals("admin")) {
//             //   System.out.println("lalala");
//                userToCompanyService.updateUserToCompany(null, 1, 2, "admin");
//            }
//        }
            if(userToCompanyList.get(loggedInUserId).getRole().equals("admin")){
                UserToCompany userToUpdate = userToCompanyList.get(user_id);
                userToCompanyService.updateUserToCompany(userToUpdate, userToUpdate.getUser_id(), userToUpdate.getCompany_id(),"admin");
                return ResponseEntity.of(Optional.of(
                      "New admin was setted"  ));
            }
        return ResponseEntity.of(Optional.of("Admin wasn`t set"));
    }
}

