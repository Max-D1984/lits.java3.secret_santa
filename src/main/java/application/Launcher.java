package application;

import Records.*;
import drivers_for_tables.DriverForCompany;
import drivers_for_tables.DriverForPresent;
import drivers_for_tables.DriverForUser;
import service.PresentService;
import service.PresentServiceImpl;
import service.RuleService;
import service.RuleServiceImpl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Launcher {
    static public List<CompanyRecords> comp = new LinkedList<CompanyRecords>();
    static public List<UserRecords> usRec = new LinkedList<UserRecords>();


    public static void main(String[] args) {
        RuleService ruleService = new RuleServiceImpl();
        ruleService.testRule();

        PresentService presentService = new PresentServiceImpl();
        presentService.testPresent();

        testCompany();
        testUser();

    }


    static void testCompany() {
        List<CompanyRecords> company;
        DriverForCompany drvCompany = new DriverForCompany("localhost:1433", "Santa", "sa", "sa");
        if (drvCompany.connectionToBase()) {
            comp = drvCompany.getDataFromTable();
            comp.stream().forEach(y -> System.out.println(y.getCompanyName()));
        }
        try {
            drvCompany.insertToTable("someCompany", "SomeDescription");
            comp = drvCompany.getDataFromTable();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        try {
            drvCompany.updateInTable(comp.get(5), "someCompany11", "SomeDescription11");
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        try {
            drvCompany.deleteFromTable(comp.get(5));
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        company = drvCompany.getDataFromTable();
        company.stream().forEach(y -> System.out.println(y.getCompanyName()));


    }


    static void testUser() {

        List<UserRecords> userRecords = new LinkedList<UserRecords>();
        DriverForUser drvUser = new DriverForUser("localhost:1433", "Santa", "sa", "sa");
        if (drvUser.connectionToBase()) {
            userRecords = drvUser.getDataFromTable();
            userRecords.stream().forEach(y -> System.out.println(y.getUserName() + " " + y.getUserRole()));
        }
        try {
            drvUser.insertToTable("someCompany", "SomeRole");
            userRecords = drvUser.getDataFromTable();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        try {
            drvUser.updateInTable(userRecords.get(1), "someCompany11", "SomeRole11");
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        try {
            drvUser.deleteFromTable(userRecords.get(1));
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        userRecords = drvUser.getDataFromTable();
        userRecords.stream().forEach(y -> System.out.println(y.getUserName()));

    }


}
