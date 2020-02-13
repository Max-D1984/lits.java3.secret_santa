package dal;

import application.Driver;
import pojo.Rule;
import pojo.UserToPresent;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserToPresentDalImpl implements UserToPresentDal {
    private static final String TABLE_NAME = "[user_to_present]";
    private static final String TABLE_USER_TO_PRESENT_COLUMN_ID = "id";
    private static final String TABLE_USER_TO_PRESENT_COLUMN_USER_ID = "user_id";
    private static final String TABLE_USER_TO_PRESENT_COLUMN_PRESENT_ID = "present_id";
    private ResultSet resultSet;
    @Override
    public void create(UserToPresent userToPresent) {
        String sql = "INSERT INTO " + TABLE_NAME + " (" + TABLE_USER_TO_PRESENT_COLUMN_USER_ID + ", " + TABLE_USER_TO_PRESENT_COLUMN_PRESENT_ID + ") VALUES(?,?)";
        try{
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,userToPresent.getUserId());
            preparedStatement.setInt(2,userToPresent.getPresentId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public UserToPresent read(int id) {
       UserToPresent userToPresent = null;
        String sql = "SELECT " + TABLE_USER_TO_PRESENT_COLUMN_ID + ", " + TABLE_USER_TO_PRESENT_COLUMN_USER_ID
                + ", " + TABLE_USER_TO_PRESENT_COLUMN_PRESENT_ID + " FROM " + TABLE_NAME + " WHERE " + TABLE_USER_TO_PRESENT_COLUMN_ID + " = ?";
        try{
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int userPresentId = resultSet.getInt(1);
                int user_id = resultSet.getInt(2);
                int present_id = resultSet.getInt(3);
                System.out.printf("%d, %d, %d \n", userPresentId, user_id, present_id);
                userToPresent = new UserToPresent(userPresentId,user_id,present_id);
            }
            return userToPresent;
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserToPresent> readByUser(int id) {
        List<UserToPresent> userToPresents = new LinkedList<>();
        try {
            String sql = "SELECT " + TABLE_USER_TO_PRESENT_COLUMN_ID + ", " + TABLE_USER_TO_PRESENT_COLUMN_USER_ID
                    + ", " + TABLE_USER_TO_PRESENT_COLUMN_PRESENT_ID + " FROM " + TABLE_NAME + " WHERE " + TABLE_USER_TO_PRESENT_COLUMN_USER_ID + " = ?";            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userToPresentId = resultSet.getInt(1);
                int user_id = resultSet.getInt(2);
                int present_id = resultSet.getInt(3);
                System.out.printf("%d, %d, %d \n", userToPresentId, user_id, present_id);
                userToPresents.add(new UserToPresent(userToPresentId, user_id, present_id));
            }
        } catch (SQLException | IOException ex) {
            System.out.println("Wrong column name or table");
            System.out.println(ex);
        }
        return userToPresents;
    }

    @Override
    public void update(int id, UserToPresent userToPresent) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<UserToPresent> readList() {
        return null;
    }
}