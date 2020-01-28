package application;

import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import pojo.Company;
import pojo.Present;
import service.*;

import java.util.*;

public class Launcher {
    public static void main(String[] args) {
//        readPresent();
//        readList();
//        createPresent();
//        updatePresent();
//        deletePresent();
//        readList();
       // showExactPresent();
        showPresentList();

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
      //  showExactCompany();
      //  showCompanyList();

    }

    static void showExactCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        Company companyFromService = companyService.readCompany(3);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", companyFromService.getCompanyName());
        variables.put("description", companyFromService.getCompanyDescription());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showExactCompany", context);
    }

    static void showCompanyList(){
        CompanyService companyService = new CompanyServiceImpl();
        List<Company> companyListFromService = companyService.readCompanyList();
        Map<String, Object> variables = new HashMap<>();
        variables.put("recordList", companyListFromService);
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showCompanyList", context);
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


    //---------Present.html---------
    static void showExactPresent() {
        PresentService presentService = new PresentServiceImpl();
        Present presentFromService = presentService.readPresent(2);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", presentFromService.getName());
        variables.put("url", presentFromService.getUrl());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showExactPresent", context);
    }

    static void showPresentList(){
        PresentService presentService = new PresentServiceImpl();
        List<Present> presentListFromService = presentService.readList();
        Map<String, Object> variables = new HashMap<>();
        variables.put("recordList", presentListFromService);
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showPresentList", context);
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

        companyService.updateCompany(companyService.readCompanyList().get(10), new Company(0, "newCompany", "newCompanyDescription"));
    }

    static void deleteCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        companyService.deleteCompany(companyService.readCompanyList().get(10));

    }


}