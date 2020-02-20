package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Company;
import service.CompanyService;
import service.CompanyServiceImpl;
import service.UserToCompanyService;
import service.UserToCompanyServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "/company")

public class CompanyController {
    public static int LOGGEDUSER = 1;

    CompanyService comp = new CompanyServiceImpl();
    @RequestMapping(
            value = "/users-company",
            method = RequestMethod.GET)
    public ResponseEntity getMyCompanys(
            @RequestParam Integer user_id) {
        UserToCompanyService userToCompanyService = new UserToCompanyServiceImpl();
        List<Integer> ids = userToCompanyService.getCompanysByUserId(LOGGEDUSER);
               comp.getUsersCompany(ids);
        return ResponseEntity.of(Optional.of(
                comp.getUsersCompany(ids)));
    }

    @RequestMapping(
            value = "/my-company",
            method = RequestMethod.GET)
    public ResponseEntity getExactCompany(
            @RequestParam Integer company_id) {
               return ResponseEntity.of(Optional.of(
                       comp.readCompany(company_id)));
    }

    @RequestMapping(
            value = "/my-company/list",
            method = RequestMethod.GET)
    public ResponseEntity getMyCompanyList() {

        return ResponseEntity.of(Optional.of(
                comp.readCompanyList()));
    }
    @RequestMapping(
            value = "/company",
            method = RequestMethod.POST)
    public ResponseEntity postCompany( @RequestParam String name, String description) {
        Company newCompany = new Company(0,name, description);
        comp.createCompany(newCompany);
        return ResponseEntity.of(Optional.of(
               "Created company" + newCompany));
    }

    @RequestMapping(
            value = "/company",
            method = RequestMethod.PUT)
    public ResponseEntity putCompany(@RequestParam int id, String newName, String newDescription) {
        Company newCompany = new Company(id, newName, newDescription);
        Company oldCompany = comp.readCompany(id);
       comp.updateCompany(oldCompany,newCompany);
        return ResponseEntity.of(Optional.of( "Update company" + oldCompany + " to " + newCompany));
    }

    @RequestMapping(
            value = "/company",
            method = RequestMethod.DELETE)
    public ResponseEntity deleteCompany(
            @RequestParam Integer id) {
        Company deletedCOmpany = comp.readCompany(11);
        comp.deleteCompany(deletedCOmpany);
        return ResponseEntity.of(Optional.of(
                "Deleted company "+ deletedCOmpany
        ));
    }

}
