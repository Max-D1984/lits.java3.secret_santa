package application;

import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import pojo.Company;
import pojo.Present;
import pojo.Rule;
import service.*;

import java.sql.Date;
import java.util.*;

public class Launcher {
    public static void main(String[] args) {
// ---------- Present -------------------------
        // readPresent();
        // readList();
        // createPresent();
        // updatePresent();
        // deletePresent();
        // readList();
        // showExactPresent();
        // showPresentList();

//        readCompany();
//        createCompany();
//        updateCompany();
//        deleteCompany();
//        readCompanyList();

//        showUpdateCompany();
//        showExactCompany();
//        showCompanyList();
//        showCreateCompany();
//        showDeleteCompany();

//        readRule();
//        readRuleList();
//        deleteRule();
//        createRule();
//        updateRule();


//
//        UserService userService = new UserServiceImpl();
//        userService.testUser();
//

    }

// ----------------Company.html -----------------------------
    static void showExactCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        Company companyFromService = companyService.readCompany(3);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", companyFromService.getCompanyName());
        variables.put("description", companyFromService.getCompanyDescription());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showExactCompany", context);
    }

    static void showUpdateCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        Company companyFromService = companyService.readCompany(3);
        Map<String, Object> variables = new HashMap<>();
        variables.put("companyName", companyFromService.getCompanyName());
        variables.put("companyDescription", companyFromService.getCompanyDescription());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showUpdateCompany", context);
    }

    static void showDeleteCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        Company companyFromService = companyService.readCompany(4);
        Map<String, Object> variables = new HashMap<>();
        variables.put("companyName", companyFromService.getCompanyName());
        variables.put("companyDescription", companyFromService.getCompanyDescription());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showDeleteCompany", context);
    }

    static void showCompanyList() {
        CompanyService companyService = new CompanyServiceImpl();
        List<Company> companyListFromService = companyService.readCompanyList();
        Map<String, Object> variables = new HashMap<>();
        variables.put("massage", "Company for Secret Santa");
        variables.put("recordList", companyListFromService);
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showCompanyList", context);
    }

    static void showCreateCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        List<Company> companyListFromService = companyService.readCompanyList();
        Map<String, Object> variables = new HashMap<>();
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showCreateCompany", context);
    }

//------------------------ Test Present----------------------------
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


//-----------------------------Present.html----------------------------------------
    static void showExactPresent() {
        PresentService presentService = new PresentServiceImpl();
        Present presentFromService = presentService.readPresent(2);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", presentFromService.getName());
        variables.put("url", presentFromService.getUrl());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showExactPresent", context);
    }

    static void showPresentList() {
        PresentService presentService = new PresentServiceImpl();
        List<Present> presentListFromService = presentService.readList();
        Map<String, Object> variables = new HashMap<>();
        variables.put("recordList", presentListFromService);
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showPresentList", context);
    }

    static void showCreatePresent() {
        PresentService presentService = new PresentServiceImpl();
        List<Present> presentListFromService = presentService.readList();
        Map<String, Object> variables = new HashMap<>();
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showCreatePresent", context);
    }

    static void showUpdatePresent() {
        PresentService presentService = new PresentServiceImpl();
        Present presentFromService = presentService.readPresent(2);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", presentFromService.getName());
        variables.put("url", presentFromService.getUrl());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showUpdatePresent", context);
    }

    static void showDeletePresent() {
        PresentService presentService = new PresentServiceImpl();
        Present presentFromService = presentService.readPresent(2);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", presentFromService.getName());
        variables.put("url", presentFromService.getUrl());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showDeletePresent", context);
    }

//------------------------ Test Company--------------------------
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
    //-----------Test Rule---------------
    static void readRule() {
       RuleService ruleService = new RuleServiceImpl();
        System.out.println(ruleService.read(3).toString());
    }

    static void readRuleList() {
        RuleService ruleService = new RuleServiceImpl();
        ruleService.readList().stream().forEach(y -> System.out.println(y.toString()));
    }

    static void createRule() {
        RuleService ruleService = new RuleServiceImpl();
        ruleService.create(new Rule(10, 10, "new Rule", Date.valueOf("2019-12-25"),200));
    }

    static void updateRule() {
        RuleService ruleService = new RuleServiceImpl();
        ruleService.update(1001, new Rule(10,10, "Old Rule", Date.valueOf("2019-12-24"),480));
    }

    static void deleteRule() {
        RuleService ruleService = new RuleServiceImpl();
        ruleService.delete(10);
    }

}