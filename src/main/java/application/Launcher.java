package application;

import Records.*;
import drivers_for_tables.DriverForCompany;
import drivers_for_tables.DriverForRules;
import drivers_for_tables.DriverForUser;

import javax.xml.namespace.QName;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Launcher {
    static public List<CompanyRecords> comp = new LinkedList<CompanyRecords>();
    static public List<RulesRecords> rule = new LinkedList<>();
    static public List<UserRecords> usRec = new LinkedList<UserRecords>();

    public static void main(String[] args) {
        // testCompany();
        testRules();
        testUser();
    }


    static void testCompany() {
        List<CompanyRecords> company;
        DriverForCompany drvCompany = new DriverForCompany("localhost:1433", "SantaBase", "sa", "sa");
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

    static void testRules() {
        List<RulesRecords> rules;
        DriverForRules drvRules = new DriverForRules("localhost:1433", "SantaBase", "sa", "sa");
        if (drvRules.connectionToBase()) {
            rule = drvRules.getDataFromTable();
            rule.stream().forEach(y -> System.out.println(y.getId()));
        }
        try {
            drvRules.insertToTable(2, "2019-12-05", "2019-12-20", "2019-12-26", 100);
            rule = drvRules.getDataFromTable();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        try {
            drvRules.updateInTable(rule.get(3), 5, "2019-12-15", "2019-12-19", "2019-12-24", 50);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        try {
            drvRules.deleteFromTable(rule.get(2));
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        rules = drvRules.getDataFromTable();
        rules.stream().forEach(y -> System.out.println(y.getId()));


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
