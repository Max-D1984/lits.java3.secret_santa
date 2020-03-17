package dal;

import application.Driver;
import model.UserResponse;
import org.springframework.stereotype.Repository;
import pojo.UserToCompany;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserToCompanyDalImpl implements UserToCompanyDal {

    private static final String TABLE_USER_TO_COMPANY_COLUMN_ID = "id";
    private static final String TABLE_USER_TO_COMPANY_COLUMN_USER_ID = "user_id";
    private static final String TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID = "company_id";
    private static final String TABLE_USER_TO_COMPANY_COMPANY_COLUMN_ROLE = "role";
    private static final String TABLE_USER_TO_COMPANY = "user_to_company";
    private ResultSet resultSet;

//
//    @PersistenceContext
//    private EntityManager manager;


    @Override
    public UserToCompany read(long id) {
        UserToCompany userToCompany = null;
        String sql = "SELECT " + TABLE_USER_TO_COMPANY_COLUMN_ID + ", " +
                TABLE_USER_TO_COMPANY_COLUMN_USER_ID + ", " + TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID +
                ", " + TABLE_USER_TO_COMPANY_COMPANY_COLUMN_ROLE +
                " FROM [" + TABLE_USER_TO_COMPANY + "] WHERE " + TABLE_USER_TO_COMPANY_COLUMN_ID + "=?";

        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int user_to_company_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_ID);
                    int user_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_USER_ID);
                    int company_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID);
                    String role = resultSet.getString(TABLE_USER_TO_COMPANY_COMPANY_COLUMN_ROLE);
                    userToCompany = new UserToCompany(user_to_company_id, user_id, company_id,role);
                }
            }
            return userToCompany;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }

//      UserToCompany userToCompany = manager.createQuery("Select u From UserToCompany u WHERE u.id= ?1", UserToCompany.class).setParameter(1,id).getSingleResult();
//
//        return userToCompany;
    }

    @Override
    public List<UserToCompany> readList() {
        List<UserToCompany> userToCompany = new LinkedList<UserToCompany>();
        String sql = "SELECT " + TABLE_USER_TO_COMPANY_COLUMN_ID + ", " + TABLE_USER_TO_COMPANY_COLUMN_USER_ID + ", "
                + TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID + ", " + TABLE_USER_TO_COMPANY_COMPANY_COLUMN_ROLE
                +  " FROM [" + TABLE_USER_TO_COMPANY + "]";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int user_to_company_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_ID);
                    int user_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_USER_ID);
                    int company_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID);
                    String role = resultSet.getString(TABLE_USER_TO_COMPANY_COMPANY_COLUMN_ROLE);
                    userToCompany.add(new UserToCompany(user_to_company_id, user_id, company_id,role));
                }
            }
            return userToCompany;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
//       List<UserToCompany> userToCompanyList = manager.createQuery("Select u From UserToCompany u", UserToCompany.class).getResultList();

//        return userToCompanyList;

    }

    @Override
    public void createUserToCompany(UserToCompany userToCompany) {
        String sql = "INSERT INTO [" + TABLE_USER_TO_COMPANY + "] (" + TABLE_USER_TO_COMPANY_COLUMN_USER_ID + ", "
                + TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID +", " + TABLE_USER_TO_COMPANY_COMPANY_COLUMN_ROLE + ") values (?,?,?)";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, userToCompany.getUser_id());
            preparedStatement.setInt(2, userToCompany.getCompany_id());
            preparedStatement.setString(3,userToCompany.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserToCompany userToCompany, int newUser_id, int newCompany_id, String newRole) {
        String sql = "UPDATE [" + TABLE_USER_TO_COMPANY + "] SET " + TABLE_USER_TO_COMPANY_COLUMN_USER_ID + "= ?, "
                + TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID +"=?, " +TABLE_USER_TO_COMPANY_COMPANY_COLUMN_ROLE + "=? WHERE "
                + TABLE_USER_TO_COMPANY_COLUMN_ID + "=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, newUser_id);
            preparedStatement.setInt(2, newCompany_id);
            preparedStatement.setString(3,newRole);
            preparedStatement.setLong(4, userToCompany.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserToCompany userToCompany) {
        String sql = "DELETE FROM [" + TABLE_USER_TO_COMPANY + "] WHERE " + TABLE_USER_TO_COMPANY_COLUMN_ID + " =  ?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, userToCompany.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserResponse> readListByCompanyId(int id) {
        List<UserResponse> usersOfCompany = new LinkedList<>();
        String sql = "SELECT  u.id, u.name, u.email from [user] u\n" +
                "join [user_to_company] uc on u.id=uc.[user_id] where uc.company_id=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int user_id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    usersOfCompany.add(new UserResponse(user_id,name,email));
                }
            }
            return usersOfCompany;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserToCompany> readListByCompanyId(long id) {
     List<UserToCompany> userToCompanyList = new LinkedList<>();
        String sql = "SELECT " + TABLE_USER_TO_COMPANY_COLUMN_ID + ", " +
                TABLE_USER_TO_COMPANY_COLUMN_USER_ID + ", " + TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID +
                ", " + TABLE_USER_TO_COMPANY_COMPANY_COLUMN_ROLE +
                " FROM [" + TABLE_USER_TO_COMPANY + "] WHERE " + TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID + "=?";

        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int user_to_company_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_ID);
                    int user_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_USER_ID);
                    int company_id = resultSet.getInt(TABLE_USER_TO_COMPANY_COLUMN_COMPANY_ID);
                    String role = resultSet.getString(TABLE_USER_TO_COMPANY_COMPANY_COLUMN_ROLE);
                    userToCompanyList.add(new UserToCompany(user_to_company_id, user_id, company_id,role));
                }
            }
            return userToCompanyList;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Integer> getUsersOfCompany(int company_id) {
        List<Integer> getUsersOfCompanyList = new LinkedList<>();
    String sql = "select user_id from [user_to_company] where company_id =?";
    try {
        PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
        preparedStatement.setLong(1, company_id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.getMetaData() != null) {
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                getUsersOfCompanyList.add(user_id);
            }
        }
        return getUsersOfCompanyList;
    } catch (SQLException | IOException e) {
        e.printStackTrace();
        return null;
    }
    }

    @Override
    public List<Integer> getCompanysByUserId(int user_id) {
        List<Integer> getUsersOfCompanyList = new LinkedList<>();
        String sql = "select company_id from [user_to_company] where user_id =?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, user_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int company_id = resultSet.getInt("company_id");
                    getUsersOfCompanyList.add(company_id);
                }
            }
            return getUsersOfCompanyList;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
