package dal;

import application.Driver;
import pojo.Present;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PresentDalImpl implements PresentDal {

    public static final String TABLE_PRESENT_COLUMN_NAME = "name";
    public static final String TABLE_PRESENT_COLUMN_URL = "url";
    public static final String TABLE_PRESENT_COLUMN_ID = "id";
    private ResultSet resultSet;

    @Override
    public void createPresent(Present present) {
        String sql = "insert into [present] (" + TABLE_PRESENT_COLUMN_NAME + ", "
                + TABLE_PRESENT_COLUMN_URL + ") values (?,?)";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, present.getName());
            preparedStatement.setString(2, present.getUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Present read(long id) {
        Present present = null;
        String sql = "select " + TABLE_PRESENT_COLUMN_ID + ", " +
                "" + TABLE_PRESENT_COLUMN_NAME + ", " + TABLE_PRESENT_COLUMN_URL +
                " from [present] where " + TABLE_PRESENT_COLUMN_ID + "=?";

        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int presentId = resultSet.getInt(TABLE_PRESENT_COLUMN_ID);
                    String name = resultSet.getString(TABLE_PRESENT_COLUMN_NAME);
                    String url = resultSet.getString(TABLE_PRESENT_COLUMN_URL);
                    present = new Present(presentId, name, url);
                }
            }
            return present;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Present present, String newName, String newUrl) {
        String sql = "UPDATE [present] SET " + TABLE_PRESENT_COLUMN_NAME + "= ?, "
                + TABLE_PRESENT_COLUMN_URL + "=? WHERE " + TABLE_PRESENT_COLUMN_ID + "=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newUrl);
            preparedStatement.setLong(3, present.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Present present) {
        String sql = "delete from [present] where " + TABLE_PRESENT_COLUMN_ID + " =  ?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, present.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Present> readList() {
        List<Present> present = new LinkedList<Present>();
        String sql = "select " + TABLE_PRESENT_COLUMN_ID + ", " + TABLE_PRESENT_COLUMN_NAME + ", "
                + TABLE_PRESENT_COLUMN_URL + " from [present]";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int presentId = resultSet.getInt(TABLE_PRESENT_COLUMN_ID);
                    String name = resultSet.getString(TABLE_PRESENT_COLUMN_NAME);
                    String url = resultSet.getString(TABLE_PRESENT_COLUMN_URL);
                    present.add(new Present(presentId, name, url));
                }
            }
            return present;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

