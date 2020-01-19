package service;

import Records.UserRecords;
import drivers_for_tables.DriverForUser;
import pojo.User;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {
    static public List<UserRecords> user = new LinkedList<>();

    @Override
    public void updateUser() {
    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void createRule(User user) {

    }

    @Override
    public void readUser() {

    }

    @Override
    public void readUserList() {

    }

    @Override
    public void testUser() {

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
