package dal;

import application.Driver;
import pojo.Hobby;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class HobbyDalImpl implements HobbyDal {
    public static final String TABLE_HOBBY_COLUMN_NAME = "name";
    public static final String TABLE_HOBBY_COLUMN_ID = "id";
    private ResultSet resultSet;

    @Override
    public Hobby read(long id) {
        Hobby hobby = null;
        String sql = "select " + TABLE_HOBBY_COLUMN_ID + ", " +
                "" + TABLE_HOBBY_COLUMN_NAME + ", " +
                " from [present] where " + TABLE_HOBBY_COLUMN_ID + "=?";

        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int hobbyId = resultSet.getInt(TABLE_HOBBY_COLUMN_ID);
                    String name = resultSet.getString(TABLE_HOBBY_COLUMN_NAME);
                    hobby = new Hobby(hobbyId, name);
                }
            }
            return hobby;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Hobby> readList() {
        List<Hobby> hobby = new LinkedList<Hobby>();
        String sql = "select " + TABLE_HOBBY_COLUMN_ID + ", " + TABLE_HOBBY_COLUMN_NAME + ", "
                + " from [present]";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int hobbyId = resultSet.getInt(TABLE_HOBBY_COLUMN_ID);
                    String name = resultSet.getString(TABLE_HOBBY_COLUMN_NAME);
                    hobby.add(new Hobby(hobbyId, name));
                }
            }
            return hobby;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createHobby (Hobby hobby) {
        String sql = "insert into [hobby] (" + TABLE_HOBBY_COLUMN_NAME + ", "
                + ") values (?,?)";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, hobby.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Hobby hobby, String newName) {
        String sql = "UPDATE [hobby] SET " + TABLE_HOBBY_COLUMN_NAME + "= ?, "
                +  "=? WHERE " +  "=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(3, hobby.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Hobby hobby) {
        String sql = "delete from [hobby] where " + TABLE_HOBBY_COLUMN_ID + " =  ?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, hobby.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
