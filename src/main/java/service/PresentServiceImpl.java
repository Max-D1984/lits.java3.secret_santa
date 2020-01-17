package service;

import Records.PresentRecords;
import drivers_for_tables.DriverForPresent;
import pojo.Present;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PresentServiceImpl implements PresentService {
    static public List<PresentRecords> pres = new LinkedList<PresentRecords>();


    @Override
    public void createPresent(Present present) {

    }

    @Override
    public void readPresent() {

    }

    @Override
    public void readPresentList() {

    }

    @Override
    public void deletePresent() {

    }

    @Override
    public void updatePresent() {

    }

    @Override
    public void testPresent() {
        List<PresentRecords> present;
        DriverForPresent drvPresent = new DriverForPresent("localhost:1433", "Santa", "sa", "sa");
        if (drvPresent.connectionToBase()) {
            pres = drvPresent.getDataFromTable();
            pres.stream().forEach(y -> System.out.println(y.getName()));
        }
        try {
            drvPresent.insertToTable("somePresent", "SomeUrl");
            pres = drvPresent.getDataFromTable();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            drvPresent.updateInTable(pres.get(1), "somePresent2", "SomeUrl2");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            drvPresent.deleteFromTable(pres.get(1));
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        present = drvPresent.getDataFromTable();
        present.stream().forEach(y -> System.out.println(y.getName()));
    }

}


