package controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.User;
import service.CompanyService;
import service.CompanyServiceImpl;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Optional;
//@EnableSwagger2
@RestController
@RequestMapping(value = "/company")

public class MyCompanyListController {
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
}
