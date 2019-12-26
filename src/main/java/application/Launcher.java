package application;

import Records.*;
import drivers_for_tables.DriverForCompany;

import javax.xml.namespace.QName;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Launcher {
    static public List<CompanyRecords> comp = new LinkedList<CompanyRecords>();

    public static void main(String[] args) {
        testCompany();
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

}
