package application;

import Records.*;
import drivers_for_tables.DriverForCompany;
import drivers_for_tables.DriverForPresent;
import drivers_for_tables.DriverForUser;
import pojo.Company;
import pojo.Present;
import service.*;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.List;

public class Launcher {
    static public List<CompanyRecords> comp = new LinkedList<CompanyRecords>();

    public static void main(String[] args) {
//        readPresent();
//        readList();
//        createPresent();
//        updatePresent();
//        deletePresent();
//        readList();

//        readCompany();
//        createCompany();
//        updateCompany();
//        deleteCompany();
//        readCompanyList();


//        RuleService ruleService = new RuleServiceImpl();
//        ruleService.readRuleList();
//
//        PresentService presentService = new PresentServiceImpl();
//        presentService.testPresent();
//
//        UserService userService = new UserServiceImpl();
//        userService.testUser();
//
//        testCompany();


    }
//--------- Test Presents-------
    static void readPresent() {
        PresentService presentService = new PresentServiceImpl();
        System.out.println(presentService.readPresent(1).toString());
    }

    static void readList() {
        PresentService presentService = new PresentServiceImpl();
        presentService.readList().stream().forEach(y -> System.out.println(y.toString()));
    }

    static void createPresent() {
        PresentService presentService = new PresentServiceImpl();
        presentService.createPresent(new Present(0, "new Present", "new Url"));
    }

    static void updatePresent() {

        PresentService presentService = new PresentServiceImpl();
        presentService.updatePresent(presentService.readList().get(12), "newNameSome", "newUrlSome");
    }

    static void deletePresent() {
        PresentService presentService = new PresentServiceImpl();
        presentService.deletePresent(presentService.readList().get(11));
        //     System.out.println(presentService.readList().get(11).toString());
    }

//--------- Test Company-------
    static void readCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        System.out.println(companyService.readCompany(3).toString());
    }

    static void readCompanyList() {
        CompanyService companyService = new CompanyServiceImpl();
        companyService.readCompanyList().stream().forEach(y -> System.out.println(y.toString()));
    }

    static void createCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        companyService.createCompany(new Company(0, "newCompany", "newDescription"));
    }

    static void updateCompany() {
        CompanyService companyService = new CompanyServiceImpl();

        companyService.updateCompany(companyService.readCompanyList().get(10),new Company(0, "newCompany", "newCompanyDescription"));
    }

    static void deleteCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        companyService.deleteCompany(companyService.readCompanyList().get(10));

    }


}