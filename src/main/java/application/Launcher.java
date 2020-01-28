package application;

import Records.*;
import drivers_for_tables.DriverForCompany;
import drivers_for_tables.DriverForPresent;
import drivers_for_tables.DriverForUser;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import pojo.Company;
import service.*;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

public class Launcher {
    static public List<CompanyRecords> comp = new LinkedList<CompanyRecords>();

    public static void main(String[] args) {
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

//        CompanyService companyService = new CompanyServiceImpl();
//        companyService.testCompany();

        showExactCompany();


    }


    private static void showExactCompany() {
        CompanyService companyService = new CompanyServiceImpl();
        Company companyFromService = companyService.readCompany(1);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", companyFromService.getCompanyName());
        variables.put("description", companyFromService.getCompanyName());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("home", context);
    }


     static void showCompanyList(){
         CompanyService companyService = new CompanyServiceImpl();
         Company companyFromService = companyService.readCompany(1);
         Map<String, Object> variables = new HashMap<>();
         variables.put("name", companyFromService.getCompanyName());
         variables.put("description", companyFromService.getCompanyName());
         IContext context = new Context(Locale.getDefault(), variables);
         ThymeleaUtils.drawPage("home", context);

    }

}


