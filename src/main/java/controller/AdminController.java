package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pojo.UserToCompany;
import service.UserService;
import service.UserServiceImpl;
import service.UserToCompanyService;
import service.UserToCompanyServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@EnableSwagger2
@RestController
@RequestMapping(value = "/")
public class AdminController {

    UserService userService = new UserServiceImpl();
    UserToCompanyService userToCompanyService = new UserToCompanyServiceImpl();

    private long loggedInUserId;


    @ApiOperation("Admin of company can set another member to admin")
    @ApiImplicitParams(
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )


    @RequestMapping(
            value = "/set-admin",
            method = RequestMethod.PUT)
    public ResponseEntity setAdmin(
            HttpServletRequest httpServletRequest,
            @RequestParam Integer company_id,
            @RequestParam Integer user_id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        loggedInUserId = (userService.readUserByEmail (userDetail.getUsername())).getId();


        List<UserToCompany> userToCompanyList = userToCompanyService.readListByCompanyId(company_id);
        for (UserToCompany user : userToCompanyList) {
            if (loggedInUserId==user.getUser_id() && user.getRole().equals("admin")) {
                for(UserToCompany updateUser: userToCompanyList){
                    if(updateUser.getUser_id()==user_id){
                        userToCompanyService.updateUserToCompany(updateUser, updateUser.getUser_id(), updateUser.getCompany_id(), "admin");
                    }
                }
                return ResponseEntity.of(Optional.of("New admin was set"));
            }
        }
        return ResponseEntity.of(Optional.of("Admin was NOT set"));
    }
    }

