package dal;

import application.Driver;
import model.UserAndUserTargetId;
import pojo.Rule;
import pojo.User;
import pojo.UserTarget;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserTargetDalImpl implements UserTargetDal {
    private static final String TABLE_NAME = "[user_target]";
    private static final String TABLE_USER_TARGET_COLUMN_ID = "id";
    private static final String TABLE_USER_TARGET_COLUMN_USER_ID = "user_" + TABLE_USER_TARGET_COLUMN_ID;
    private static final String TABLE_USER_TARGET_COLUMN_USER_TARGET_ID = "user_target_" + TABLE_USER_TARGET_COLUMN_ID;
    private static final String TABLE_USER_TARGET_COLUMN_STATUS = "status";
    private ResultSet resultSet;

    @Override
    public void create(UserTarget userTarget) {
        String sql = "INSERT INTO " + TABLE_NAME + " (" + TABLE_USER_TARGET_COLUMN_USER_ID + ", " + TABLE_USER_TARGET_COLUMN_USER_TARGET_ID + ", " + TABLE_USER_TARGET_COLUMN_STATUS + ") VALUES(?,?,?)";
        try{
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,userTarget.getUserId());
            preparedStatement.setInt(2,userTarget.getUserTargetId());
            preparedStatement.setString(3,userTarget.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public UserTarget read(int id) {
        UserTarget userTarget = null;
        String sql = "SELECT " + TABLE_USER_TARGET_COLUMN_ID + ", " + TABLE_USER_TARGET_COLUMN_USER_ID
                + ", " + TABLE_USER_TARGET_COLUMN_USER_TARGET_ID + ", " + TABLE_USER_TARGET_COLUMN_STATUS
                + " FROM " + TABLE_NAME + " WHERE " + TABLE_USER_TARGET_COLUMN_ID + " = ?";
        try{
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id1 = resultSet.getInt(1);
                int user_id = resultSet.getInt(2);
                int user_target_id = resultSet.getInt(3);
                String status = resultSet.getString(4);
                System.out.printf("%d, %d, %d, %s \n", id1, user_target_id, status);
                userTarget = new UserTarget(id1,user_id,user_target_id,status);
            }
            return userTarget;
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, UserTarget userTarget) {
        String sql = "UPDATE " + TABLE_NAME + " SET " + TABLE_USER_TARGET_COLUMN_USER_ID + "= ?, " + TABLE_USER_TARGET_COLUMN_USER_TARGET_ID + "=?, " + TABLE_USER_TARGET_COLUMN_STATUS + "=? WHERE " + TABLE_USER_TARGET_COLUMN_ID + "=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,userTarget.getUserId());
            preparedStatement.setInt(2, userTarget.getUserTargetId());
            preparedStatement.setString(3,userTarget.getStatus());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            System.out.println("Updating userTarget is failed");
            System.out.println(ex);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + TABLE_USER_TARGET_COLUMN_ID + " =  ?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            System.out.println("Deleting userTarger is failed");
            System.out.println(ex);
        }
    }

    @Override
    public List<UserTarget> readList() {
        List<UserTarget> userTargets = new LinkedList<>();
        try {
            String sql = "SELECT " + TABLE_USER_TARGET_COLUMN_ID + ", " + TABLE_USER_TARGET_COLUMN_USER_ID + ", " + TABLE_USER_TARGET_COLUMN_USER_TARGET_ID + ", " + TABLE_USER_TARGET_COLUMN_STATUS + " FROM " + TABLE_NAME;
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int user_id = resultSet.getInt(2);
                int user_target_id = resultSet.getInt(3);
                String status = resultSet.getString(4);
                System.out.printf("%d, %d, %d, %s \n", id, user_id, user_target_id, status);
                userTargets.add(new UserTarget(id, user_id, user_target_id, status));
            }
        } catch (SQLException | IOException ex) {
            System.out.println("Wrong column name or table");
            System.out.println(ex);
        }
        return userTargets;
    }

    @Override
    public void createList(Map<Integer,Integer> mapOfSecretSanta) {
        Set mapSet = mapOfSecretSanta.entrySet();
        Iterator iterator = mapSet.iterator();
        while(iterator.hasNext()){
            Map.Entry entry=(Map.Entry)iterator.next();
            String sql = "INSERT INTO " + TABLE_NAME + " (" + TABLE_USER_TARGET_COLUMN_USER_ID
                    + ", " + TABLE_USER_TARGET_COLUMN_USER_TARGET_ID
                    + ", " + TABLE_USER_TARGET_COLUMN_STATUS + ") VALUES(?,?,?)";
            try{
                PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, (Integer) entry.getKey());
                preparedStatement.setInt(2, (Integer) entry.getValue());
                preparedStatement.setString(3,"OK");
                preparedStatement.executeUpdate();
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }

        }
    }


    public List getTargetForUserById (int id) {
       List listOfTargetsId = new LinkedList();
        String sql = "select user_target_id from [user_target] where user_id = ?";
        try{
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.toString());
            while(resultSet.next()){
                int id1 = resultSet.getInt("user_target_id");
                listOfTargetsId.add(id1);
            }
            return listOfTargetsId;
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return listOfTargetsId;
    }

    @Override
    public List<Integer> getTargetsIdByUsersId(List<Integer> usersIds) {
        List<Integer> targetsIdList = new LinkedList<>();
        int count=0;
        String strId="";
        for (int id:usersIds) {
            if(count!=usersIds.size()-1){
                strId = strId+id+", ";
            }else{
                strId = strId+id;
            }
            count++;
        }
        String sql = "select user_target_id from [user_target] where user_id in ("+strId+")";
        try{
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
          //  preparedStatement.setString(1,strId);
            resultSet = preparedStatement.executeQuery();
          //  System.out.println(resultSet.toString());
            while(resultSet.next()){
                int id1 = resultSet.getInt("user_target_id");
                targetsIdList.add(id1);
            }
            return targetsIdList;
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }
    @Override
    public List<UserAndUserTargetId> getTargetForUserInCompany(int user_id, List<Integer> target_id) {
        List<UserAndUserTargetId> userAndUserTargetIds = new LinkedList<>();
        int count=0;
        String strId="";
        for (int id:target_id) {
            if(count!=target_id.size()-1){
                strId = strId+id+", ";
            }else{
                strId = strId+id;
            }
            count++;
        }
        String sql = "select * from user_target where user_id = "+ user_id+" and user_target_id in ("+strId+")";

        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int get_user_id = resultSet.getInt("user_id");
                    int get_target_id = resultSet.getInt("user_target_id");
                    userAndUserTargetIds.add(new UserAndUserTargetId(get_user_id, get_target_id));
                }
            }
            return userAndUserTargetIds;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Integer> getSantasInCompany(Integer companyId) {
        List listOfSantasId = new LinkedList();
        String sql = "select user_id from [user_target] where company_id = ?";
        try{
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, companyId);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.toString());
            while(resultSet.next()){
                int id1 = resultSet.getInt("user_id");
                listOfSantasId.add(id1);
            }
            return listOfSantasId;
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return listOfSantasId;
    }
}



