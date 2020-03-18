package dal;

import model.TargetUserIdAndCompanyId;
import pojo.User;
import application.Driver;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class UserDalImpl implements UserDal {
    public static final String COLUMN_TABLE_NAME_ID = "id";
    public static final String COLUMN_TABLE_NAME_EMAIL = "email";
    public static final String COLUMN_TABLE_NAME_NAME = "name";
    public static final String COLUMN_TABLE_NAME_PASSWORD = "password";
    private ResultSet resultSet;

    @Override
    public void create(User user) {
        String sql = "insert into [User] (" + COLUMN_TABLE_NAME_NAME + ", " + COLUMN_TABLE_NAME_EMAIL + ", " + COLUMN_TABLE_NAME_PASSWORD + ") values (?,?,?)";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassWord());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(long id) {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Driver.getConnection().prepareStatement("select * from [user] where " + COLUMN_TABLE_NAME_ID + "=?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(COLUMN_TABLE_NAME_NAME);
                Integer userId = rs.getInt(COLUMN_TABLE_NAME_ID);
                String email = rs.getString(COLUMN_TABLE_NAME_EMAIL);
                String passWord = rs.getString(COLUMN_TABLE_NAME_PASSWORD);
                user = new User(userId, name, email, passWord);
            }
            return user;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User readUserByEmail(String email) {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Driver.getConnection().prepareStatement("select * from [user] where " + COLUMN_TABLE_NAME_EMAIL + " =?");
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name  = rs.getString(COLUMN_TABLE_NAME_NAME);
                Integer userId = rs.getInt(COLUMN_TABLE_NAME_ID);
                String userEmail = rs.getString(COLUMN_TABLE_NAME_EMAIL);
                String passWord = rs.getString(COLUMN_TABLE_NAME_PASSWORD);
                user = new User(userId, name, email, passWord);
            }
            return user;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public void update(User user, User newUser) {
        String sql = "UPDATE [user] SET " + COLUMN_TABLE_NAME_NAME + "=?, " + COLUMN_TABLE_NAME_EMAIL + "=?, " + COLUMN_TABLE_NAME_PASSWORD + "=? WHERE " + COLUMN_TABLE_NAME_ID + "=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, newUser.getUserName());

            preparedStatement.setLong(2, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User user) {
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement("delete from [user] where " + COLUMN_TABLE_NAME_ID + "=?");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> readList() {
        List<User> userList = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement("select * from [User]");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(COLUMN_TABLE_NAME_NAME);
                Integer userId = rs.getInt(COLUMN_TABLE_NAME_ID);
                String email = rs.getString(COLUMN_TABLE_NAME_EMAIL);
                String passWord = rs.getString(COLUMN_TABLE_NAME_PASSWORD);
                userList.add(new User(userId, name, email, passWord));
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getUsersNamesbyIdCompany(List<Integer> listOfId) {
        int count=0;
        String strId="";
        for (int id:listOfId) {
            if(count!=listOfId.size()-1){
                strId = strId+id+", ";
            }else{
                strId = strId+id;
            }
            count++;

        }
List<String> namesOfUsers = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(
                    "select " + COLUMN_TABLE_NAME_NAME + " from [user] where " + COLUMN_TABLE_NAME_ID + " in (" +strId+")"
            );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(COLUMN_TABLE_NAME_NAME);
                namesOfUsers.add(name);
            }
            return namesOfUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<TargetUserIdAndCompanyId> getAllTargetsForUser(List<Integer> listOfCompanys, List<Integer> listOfTargets) {

        List<TargetUserIdAndCompanyId> namesOfUsers = new LinkedList<>();

        String stringOfTargetsId = doStringFromList(listOfTargets);
        String stringOfCompanysId = doStringFromList(listOfCompanys);


        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(
                    "select user_" + COLUMN_TABLE_NAME_ID + ", company_id from user_to_company where " +
                            "user_" + COLUMN_TABLE_NAME_ID + " in (" + stringOfTargetsId+")" +
                            "and " +
                            "company_" + COLUMN_TABLE_NAME_ID + " in (" +stringOfCompanysId+")"
            );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer name = rs.getInt("user_" + COLUMN_TABLE_NAME_ID);
                Integer company = rs.getInt("company_" + COLUMN_TABLE_NAME_ID);

                namesOfUsers.add(new TargetUserIdAndCompanyId(name, company));
            }
            return namesOfUsers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String doStringFromList (List<Integer> currentListId){
        int count=0;
        String strId="";
        for (int id:currentListId) {
            if(count!=currentListId.size()-1){
                strId = strId+id+", ";
            }else{
                strId = strId+id;
            }
            count++;

        }
        return strId;
    }

    //  select user_id, company_id from user_to_company where
}


