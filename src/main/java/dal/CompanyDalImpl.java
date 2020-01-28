package dal;

import pojo.Company;
import application.Driver;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CompanyDalImpl implements CompanyDal {

    private ResultSet resultSet;



    @Override
    public void create(Company company) {
        String sql = "INSERT INTO [company1] (name, description) VALUES (?,?)";
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
            String sql = "SELECT id, name, description FROM [company1] WHERE id=?";
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int compId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    company = new Company(compId, name, description);
                }

            }
            return company;
          } catch (SQLException | IOException ex) {
            System.out.println("Wrong column name or table");
            return null;
        }

    }

    @Override
    public void update(Company oldCompany, Company newCompany) {
        String sql = "UPDATE [company1] SET name= ?, description=? WHERE id=?";
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
        String sql = "DELETE FROM [company1] WHERE id =  ?";
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
            String sql = "SELECT id, name, description FROM [company1]";
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getMetaData() != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    company.add(new Company(id, name, description));
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println("Wrong column name or table");
        }
        return company;
    }
}
