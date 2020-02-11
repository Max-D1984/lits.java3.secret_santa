package application;

import org.slf4j.Logger;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import pojo.*;
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
        // showCreatePresent();
        // showUpdatePresent();
        // showDeletePresent();


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

//          readUser();
//        readUserList();
//        createUser();
//
//        readUserList();
//
//        updateUser();
//        readUserList();
//
//       deleteUser();
//        readUserList();
//

  //    showUpdateUser();
   //  showExactUser();
   // showUserList();
    //   showCreateUser();
//        showDeleteUser();
//

  //   readHobby();
    //    createHobby();
    //    updateHobby();
    //    deleteHobby();
      //  readHobbyList();

  //      showUpdateHobby();
   //     showExactHobby();
   //    showHobbyList();
   //    showCreateHobby();
   //     showDeleteHobby();

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
    static void showExactUser() {
        UserService userService = new UserServiceImpl();
        User userFromService = userService.readUser(10);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", userFromService.getUserName());
        variables.put("role", userFromService.getUserRole());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showExactCompany", context);
    }

    static void showUpdateUser() {
        UserService userService = new UserServiceImpl();
        User userFromService = userService.readUser(8);
        Map<String, Object> variables = new HashMap<>();
        variables.put("companyName", userFromService.getUserName());
        variables.put("companyRole", userFromService.getUserRole());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showUpdateUser", context);
    }

    static void showDeleteUser() {
        UserService userService = new UserServiceImpl();
        User userFromService = userService.readUser(3);
        Map<String, Object> variables = new HashMap<>();
        variables.put("userName", userFromService.getUserName());
        variables.put("userRole", userFromService.getUserRole());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showDeleteCompany", context);
    }

    static void showUserList() {
        UserService userService = new UserServiceImpl();;
        List<User> userListFromService = userService.readUserList();
        Map<String, Object> variables = new HashMap<>();
        variables.put("massage", "User for Secret Santa");
        variables.put("recordList", userListFromService);
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showUserList", context);
    }

    static void showCreateUser() {
        UserService userService = new UserServiceImpl();
        List<User> companyListFromService = userService.readUserList();
        Map<String, Object> variables = new HashMap<>();
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showCreateUser", context);
}
    static void readUser() {
        UserService userService = new UserServiceImpl();
        System.out.println( userService.readUser(1));
    }

    static void readUserList() {
        UserService userService = new UserServiceImpl();
        userService.readUserList().stream().forEach(y -> System.out.println(y.toString()));
    }

    static void createUser() {
        UserService userService = new UserServiceImpl();
        userService.createUser(new User(0, "new User", "new Role"));
    }

    static void updateUser() {

        UserService userService = new UserServiceImpl();
       userService.updateUser(userService.readUserList().get(5), new User (1, "newName", "newRole"));
           }

    static void deleteUser() {
        UserService userService = new UserServiceImpl();
       userService.deleteUser(userService.readUserList().get(5));
    }

// ------------------testHobby-----------------
static void readHobby() {
    HobbyService hobbyService = new HobbyServiceImpl();
    System.out.println(hobbyService.readHobby(1).toString());
}

    static void readHobbyList() {
        HobbyService hobbyService = new HobbyServiceImpl();
        hobbyService.readList().stream().forEach(y -> System.out.println(y.toString()));
    }

    static void createHobby() {
        HobbyService hobbyService = new HobbyServiceImpl();
        hobbyService.createHobby(new Hobby(0, "new Hobby"));
    }

    static void updateHobby() {
        HobbyService hobbyService = new HobbyServiceImpl();
        hobbyService.updateHobby(hobbyService.readList().get(1), "newSomeHobby");
    }

    static void deleteHobby() {
        HobbyService hobbyService = new HobbyServiceImpl();
        hobbyService.deleteHobby(hobbyService.readList().get(1));
        //     System.out.println(presentService.readList().get(11).toString());
    }

    //-----------------------------Present.html----------------------------------------
    static void showExactHobby() {
        HobbyService hobbyService = new HobbyServiceImpl();
        Hobby hobbyFromService = hobbyService.readHobby(2);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", hobbyFromService.getName());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showExactHobby", context);
    }

    static void showHobbyList() {
        HobbyService hobbyService = new HobbyServiceImpl();
        List<Hobby> hobbyListFromService = hobbyService.readList();
        Map<String, Object> variables = new HashMap<>();
        variables.put("recordList", hobbyListFromService);
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showHobbyList", context);
    }

    static void showCreateHobby() {
        HobbyService hobbyService = new HobbyServiceImpl();
        List<Hobby> hobbyListFromService = hobbyService.readList();
        Map<String, Object> variables = new HashMap<>();
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showCreateHobby", context);
    }

    static void showUpdateHobby() {
        HobbyService hobbyService = new HobbyServiceImpl();
        Hobby hobbyFromService = hobbyService.readHobby(2);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", hobbyFromService.getName());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showUpdateHobby", context);
    }

    static void showDeleteHobby() {
        HobbyService hobbyService = new HobbyServiceImpl();
        Hobby hobbyFromService = hobbyService.readHobby(2);
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", hobbyFromService.getName());
        IContext context = new Context(Locale.getDefault(), variables);
        ThymeleaUtils.drawPage("showDeleteHobby", context);
    }


}