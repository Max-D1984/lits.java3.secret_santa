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

    private ResultSet resultSet;

    @Override
    public void createPresent(Present present) {
        String sql = "insert into [present] (name, url) values (?,?)";
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
        String sql = "select id, name, url from [present] where id=?";

        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int presentId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String url = resultSet.getString("url");
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
    public void update(Present present, String newName, String newUrl ) {
        String sql = "UPDATE [present] SET name= ?, url=? WHERE id=?";
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
        String sql = "delete from [present] where id =  ?";
        try {
            PreparedStatement preparedStatement =  Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, present.getId());
            preparedStatement.executeUpdate();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }


    @Override
    public List<Present> readList() {
        List<Present> present = new LinkedList<Present>();
        String sql = "select id, name, url from [present]";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int presentId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String url = resultSet.getString("url");
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

