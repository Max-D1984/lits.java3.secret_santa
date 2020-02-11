package dal;

import pojo.Company;
import application.Driver;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CompanyDalImpl implements CompanyDal {

    public static final String TABLE_COMPANY_COLUMN_ID = "id";
    public static final String TABLE_COMPANY_COLUMN_NAME = "name";
    public static final String TABLE_COMPANY_COLUMN_DESCRIPTION = "description";
    private ResultSet resultSet;



    @Override
    public void create(Company company) {
        String sql = "INSERT INTO [company] (" + TABLE_COMPANY_COLUMN_NAME + ", " + TABLE_COMPANY_COLUMN_DESCRIPTION +
                ") VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(2, company.getCompanyDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            System.out.println("Company creation failed");
        }
    }

    @Override
    public Company read(long id) {
        Company company = null;
        try {
            String sql = "SELECT " + TABLE_COMPANY_COLUMN_ID + ", " + TABLE_COMPANY_COLUMN_NAME + ", " +
                    TABLE_COMPANY_COLUMN_DESCRIPTION + " FROM [company] WHERE "+ TABLE_COMPANY_COLUMN_ID +"=?";
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int compId = resultSet.getInt(TABLE_COMPANY_COLUMN_ID);
                    String name = resultSet.getString(TABLE_COMPANY_COLUMN_NAME);
                    String description = resultSet.getString(TABLE_COMPANY_COLUMN_DESCRIPTION);
                    company = new Company(compId, name, description);
                }

            }
            return company;
          } catch (SQLException | IOException ex) {
            System.out.println("Wrong column " + TABLE_COMPANY_COLUMN_NAME + " or table");
            return null;
        }

    }

    @Override
    public void update(Company oldCompany, Company newCompany) {
        String sql = "UPDATE [company] SET " + TABLE_COMPANY_COLUMN_NAME + "= ?, " + TABLE_COMPANY_COLUMN_DESCRIPTION +
                "=? WHERE " + TABLE_COMPANY_COLUMN_ID + "=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, newCompany.getCompanyName());
            preparedStatement.setString(2, newCompany.getCompanyDescription());
            preparedStatement.setLong(3, oldCompany.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            System.out.println("Updating company is failed");
        }
    }


    @Override
    public void delete(Company company) {
        String sql = "DELETE FROM [company] WHERE " + TABLE_COMPANY_COLUMN_ID + " =  ?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, company.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            System.out.println("Deleting company is failed");
        }
    }

    @Override
    public List<Company> readList() {
        List<Company> company = new LinkedList<Company>();
        try {
            String sql = "SELECT " + TABLE_COMPANY_COLUMN_ID + ", " + TABLE_COMPANY_COLUMN_NAME + ", " +
                    TABLE_COMPANY_COLUMN_DESCRIPTION + " FROM [company]";
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(TABLE_COMPANY_COLUMN_ID);
                    String name = resultSet.getString(TABLE_COMPANY_COLUMN_NAME);
                    String description = resultSet.getString(TABLE_COMPANY_COLUMN_DESCRIPTION);
                    company.add(new Company(id, name, description));
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println("Wrong column " + TABLE_COMPANY_COLUMN_NAME + " or table");
        }
        return company;
    }
}
