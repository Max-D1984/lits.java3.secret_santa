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
    public static final String TABLE_HOBBY = "hobby";
    private ResultSet resultSet;

    @Override
    public Hobby read(long id) {
        Hobby hobby = null;
        String sql = "select " + TABLE_HOBBY_COLUMN_ID + ", " +
                TABLE_HOBBY_COLUMN_NAME +
                 " from [" + TABLE_HOBBY + "] where " + TABLE_HOBBY_COLUMN_ID + "=?";

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
        String sql = "select " + TABLE_HOBBY_COLUMN_ID + ", " + TABLE_HOBBY_COLUMN_NAME
                + " from [" + TABLE_HOBBY + "]";
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
        String sql = "insert into [" + TABLE_HOBBY + "] (" + TABLE_HOBBY_COLUMN_NAME
                + ") values (?)";
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
        String sql = "UPDATE [" + TABLE_HOBBY + "] SET " + TABLE_HOBBY_COLUMN_NAME + "=?"
                +  " WHERE " + TABLE_HOBBY_COLUMN_ID + "=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, hobby.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Hobby hobby) {
        String sql = "delete from [" + TABLE_HOBBY + "] where " + TABLE_HOBBY_COLUMN_ID + " =  ?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, hobby.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Hobby> readListByHobbysId(List<Integer> id) {
        List<Hobby> hobby = new LinkedList<Hobby>();
        String sql = "select " + TABLE_HOBBY_COLUMN_ID + ", " + TABLE_HOBBY_COLUMN_NAME +
                " from [" + TABLE_HOBBY + "] where id in (" + doStringFromList(id) + ")";
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
}
