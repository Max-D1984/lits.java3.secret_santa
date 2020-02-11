package dal;

import application.Driver;
import pojo.UserToCompany;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserToCompanyDalImpl implements UserToCompanyDal {

    public static final String TABLE_USER_TO_COMPANY_COLUMN_USER_ID = "user_id";
    public static final String TABLE_USER_TO_COMPANY_COLUMN_HOBBY_ID = "hobby_id";
    public static final String TABLE_USER_TO_COMPANY_COLUMN_ID = "id";
    public static final String TABLE_USER_TO_COMPANY = "user_to_company";
    private ResultSet resultSet;

    @Override
    public UserToCompany read(long id) {
        UserToCompany userToCompany = null;
        String sql = "select " + TABLE_USER_TO_COMPANY_COLUMN_HOBBY_ID + ", " +
                "" + TABLE_USER_TO_COMPANY_COLUMN_USER_ID + ", " + TABLE_USER_TO_COMPANY_COLUMN_HOBBY_ID +
                " from [" + TABLE_USER_TO_COMPANY + "] where " + TABLE_USER_TO_COMPANY_COLUMN_ID + "=?";

        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int presentId = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_ID);
                    int user_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_USER_ID);
                    int hobby_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_HOBBY_ID);
                    userToCompany = new UserToCompany(1, 1, 1);
                }
            }
            return userToCompany;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserToCompany> readList() {
        List<UserToCompany> userToHobby = new LinkedList<UserToCompany>();
        String sql = "select " + TABLE_USER_TO_COMPANY_COLUMN_ID + ", " + TABLE_USER_TO_COMPANY_COLUMN_USER_ID + ", "
                + TABLE_USER_TO_COMPANY_COLUMN_HOBBY_ID + " from [" + TABLE_USER_TO_COMPANY + "]";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int presentId = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_ID);
                    int user_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_USER_ID);
                    int hobby_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_HOBBY_ID);
                    userToHobby.add(new UserToCompany(1, 1, 1));
                }
            }
            return userToHobby;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createUserToCompany(UserToCompany userToCompany) {
        String sql = "insert into [" + TABLE_USER_TO_COMPANY + "] (" + TABLE_USER_TO_COMPANY_COLUMN_USER_ID + ", "
                + TABLE_USER_TO_COMPANY_COLUMN_HOBBY_ID + ") values (?,?)";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, userToCompany.getUser_id());
            preparedStatement.setInt(2, userToCompany.getCompany_id());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserToCompany userToHobby, int newUser_id, int newHobby_id) {
        String sql = "UPDATE [" + TABLE_USER_TO_COMPANY + "] SET " + TABLE_USER_TO_COMPANY_COLUMN_USER_ID + "= ?, "
                + TABLE_USER_TO_COMPANY_COLUMN_HOBBY_ID + "=? WHERE " + TABLE_USER_TO_COMPANY_COLUMN_ID + "=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, newUser_id);
            preparedStatement.setInt(2, newHobby_id);
            preparedStatement.setLong(3, userToHobby.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserToCompany userToHobby) {
        String sql = "delete from [" + TABLE_USER_TO_COMPANY + "] where " + TABLE_USER_TO_COMPANY_COLUMN_ID + " =  ?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, userToHobby.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}