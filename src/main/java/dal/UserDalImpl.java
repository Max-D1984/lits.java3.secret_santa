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
    private ResultSet resultSet;

    @Override
    public void create(User user) {
        String sql = "insert into [User] (name) values (?)";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
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
            preparedStatement = Driver.getConnection().prepareStatement("select * from [user] where id=?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                Integer userId = rs.getInt("id");
                user = new User(userId, name);

            }
            return user;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public void update(User user, User newUser) {
        String sql = "UPDATE [user] SET name=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User user) {
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement("delete from [user] where id=?");
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
                String name = rs.getString("name");
                Integer userId = rs.getInt("id");
                userList.add(new User(userId, name));
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
                    "select name from [user] where id in ("+strId+")"
            );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
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
                    "select user_id, company_id from user_to_company where " +
                            "user_id in ("+ stringOfTargetsId+")" +
                            "and " +
                            "company_id in ("+stringOfCompanysId+")"
            );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("user_id");
                String company = rs.getString("company_id");

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


