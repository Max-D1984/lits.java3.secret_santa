package dal;

import application.Driver;
import pojo.UserToHobby;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserToHobbyDalImpl implements UserToHobbyDal {

    public static final String TABLE_USER_TO_HOBBY_COLUMN_USER_ID = "user_id";
    public static final String TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID = "hobby_id";
    public static final String TABLE_USER_TO_HOBBY_COLUMN_ID = "id";
    private ResultSet resultSet;

    @Override
    public UserToHobby read(long id) {
        UserToHobby userToHobby = null;
        String sql = "select " + TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID + ", " +
                "" + TABLE_USER_TO_HOBBY_COLUMN_USER_ID + ", " + TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID +
                " from [user_to_hobby] where " + TABLE_USER_TO_HOBBY_COLUMN_ID + "=?";

        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int presentId = resultSet.getInt(TABLE_USER_TO_HOBBY_COLUMN_ID);
                    int user_id = resultSet.getInt(TABLE_USER_TO_HOBBY_COLUMN_USER_ID);
                    int hobby_id = resultSet.getInt(TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID);
                    userToHobby = new UserToHobby(1, 1, 1);
                }
            }
            return userToHobby;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserToHobby> readList() {
        List<UserToHobby> userToHobby = new LinkedList<UserToHobby>();
        String sql = "select " + TABLE_USER_TO_HOBBY_COLUMN_ID + ", " + TABLE_USER_TO_HOBBY_COLUMN_USER_ID + ", "
                + TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID + " from [user_to_hobby]";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int presentId = resultSet.getInt(TABLE_USER_TO_HOBBY_COLUMN_ID);
                    int user_id = resultSet.getInt(TABLE_USER_TO_HOBBY_COLUMN_USER_ID);
                    int hobby_id = resultSet.getInt(TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID);
                    userToHobby.add(new UserToHobby(1, 1, 1));
                }
            }
            return userToHobby;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserToHobby> readListByUserId(long userId) {
        List<UserToHobby> userToHobby = new LinkedList<UserToHobby>();
        String sql = "select " + TABLE_USER_TO_HOBBY_COLUMN_ID + ", " + TABLE_USER_TO_HOBBY_COLUMN_USER_ID + ", "
                + TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID + " from [user_to_hobby] WHERE " + TABLE_USER_TO_HOBBY_COLUMN_USER_ID + "=?";;
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, (int)userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(TABLE_USER_TO_HOBBY_COLUMN_ID);
                    int user_id = resultSet.getInt(TABLE_USER_TO_HOBBY_COLUMN_USER_ID);
                    int hobby_id = resultSet.getInt(TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID);
                    userToHobby.add(new UserToHobby(id, user_id, hobby_id));
                }
            }
            return userToHobby;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void createUserToHobby(UserToHobby userToHobby) {
        String sql = "insert into [user_to_hobby] (" + TABLE_USER_TO_HOBBY_COLUMN_USER_ID + ", "
                + TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID + ") values (?,?)";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, userToHobby.getUser_id());
            preparedStatement.setInt(2, userToHobby.getHobby_id());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserToHobby userToHobby, int newUser_id, int newHobby_id) {
        String sql = "UPDATE [user_to_hobby] SET " + TABLE_USER_TO_HOBBY_COLUMN_USER_ID + "= ?, "
                + TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID + "=? WHERE " + TABLE_USER_TO_HOBBY_COLUMN_ID + "=?";
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
    public void delete(UserToHobby userToHobby) {
        String sql = "delete from [user_to_hobby] where " + TABLE_USER_TO_HOBBY_COLUMN_USER_ID + " =  ? AND " + TABLE_USER_TO_HOBBY_COLUMN_HOBBY_ID + " = ?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, userToHobby.getUser_id());
            preparedStatement.setLong(2, userToHobby.getHobby_id());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
