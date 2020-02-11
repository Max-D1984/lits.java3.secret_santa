package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Company;
import service.CompanyService;
import service.CompanyServiceImpl;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "/company")

public class CompanyController {
    CompanyService comp = new CompanyServiceImpl();
    @RequestMapping(
            value = "/my-company",
            method = RequestMethod.GET)
    public ResponseEntity getMyCompany(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                comp.readCompany(id)));
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
    public ResponseEntity postPresentList() {
        return ResponseEntity.of(Optional.of(
                new Company(1,
                        "SomeCompanyNamePUT",
                        "SOmeCompanyDescription")));
    }

    @RequestMapping(
            value = "/company",
            method = RequestMethod.PUT)
    public ResponseEntity putPresentList() {
        return ResponseEntity.of(Optional.of(
                new Company(1,
                        "SomeCompanyNamePOST",
                        "Hello")));
    }

    @RequestMapping(
            value = "/company",
            method = RequestMethod.DELETE)
    public ResponseEntity deletePresentList(
            @RequestParam Integer id) {
        return ResponseEntity.of(Optional.of(
                new Company(1,
                        "SomePresentNameDELETE",
                        "http://Hellooooo")));
    }

}
