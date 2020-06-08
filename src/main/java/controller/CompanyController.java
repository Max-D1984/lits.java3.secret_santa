package controller;

import application.Constants;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pojo.Company;
import pojo.UserToCompany;

import service.*;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "/company")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController {

    private UserService userService = new UserServiceImpl();
    private CompanyService companyService = new CompanyServiceImpl();
    private UserToCompanyService userToCompanyService = new UserToCompanyServiceImpl();

//    @Autowired
//    private UserService userService;
//    @Autowired
//    private CompanyService companyService;
//    @Autowired
//    private UserToCompanyService userToCompanyService;
//
//    public UserToCompanyService getUserToCompanyService() {
//        return userToCompanyService;
//    }
//
//    public CompanyService getCompanyService() {
//        return companyService;
//    }
//
//    public UserService getUserService() {
//        return userService;
//    }



    @ApiOperation("List of company for logged user")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/users-company",
            method = RequestMethod.GET)
    public ResponseEntity getMyCompanys(
            @RequestParam Integer user_id) {

        List<Integer> ids = userToCompanyService.getCompanysByUserId(Constants.LOGGEDUSER);
        companyService.getUsersCompany(ids);
        return ResponseEntity.of(Optional.of(
                companyService.getUsersCompany(ids)));
    }

    @ApiOperation("Information about exact company")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/my-company",
            method = RequestMethod.GET)
    public ResponseEntity getExactCompany(
            @RequestParam Integer company_id) {
        return ResponseEntity.of(Optional.of(
                companyService.readCompany(company_id)));
    }

    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
//    @RequestMapping(
//            value = "/my-company/list",
//            method = RequestMethod.GET)
    public ResponseEntity getMyCompanyList() {
        return ResponseEntity.of(Optional.of(
                companyService.readCompanyList()));
    }
    @ApiOperation("Create new company")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/company",
            method = RequestMethod.POST)
    public ResponseEntity postCompany(@RequestParam String name,
                                      @RequestParam String description,
                                      @RequestParam Integer loggedUserId,
                                      @RequestParam("file") MultipartFile file) throws IOException {

        Company newCompany = new Company(0, name, description,file.getBytes());
        companyService.createCompany(newCompany);
        List<Company> allCompany = companyService.readCompanyList();
        Company lastCompany = allCompany.get(allCompany.size() - 1);

        userToCompanyService.createUserToCompany(new UserToCompany(0, Constants.LOGGEDUSER, lastCompany.getId(), "admin"));

        return ResponseEntity.of(Optional.of(
                "Created company " + lastCompany.getCompanyName() + " admin: " + userService.readUser(Constants.LOGGEDUSER)));
    }

    @ApiOperation("Create new company(test)")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(
            value = "/companytest",
            method = RequestMethod.POST)
    public ResponseEntity createCompanyTest(@RequestParam String name,
                                      @RequestParam String description,
                                      @RequestParam("file") MultipartFile file) throws IOException {

        Company newCompany = new Company(0, name, description,file.getBytes());
        companyService.createCompany(newCompany);

        return ResponseEntity.of(Optional.of(
                "The creation of the company was successful" ));
    }

//    @RequestMapping(
//            value = "/company",
//            method = RequestMethod.PUT)
    public ResponseEntity putCompany(@RequestParam int id, String newName, String newDescription) {
        Company newCompany = new Company(id, newName, newDescription,null);
        Company oldCompany = companyService.readCompany(id);
        companyService.updateCompany(oldCompany, newCompany);
        return ResponseEntity.of(Optional.of("Update company" + oldCompany + " to " + newCompany));
    }

//    @RequestMapping(
//            value = "/company",
//            method = RequestMethod.DELETE)
    public ResponseEntity deleteCompany(
            @RequestParam Integer id) {
        Company deletedCOmpany = companyService.readCompany(11);
        companyService.deleteCompany(deletedCOmpany);
        return ResponseEntity.of(Optional.of(
                "Deleted company " + deletedCOmpany
        ));
    }

}
