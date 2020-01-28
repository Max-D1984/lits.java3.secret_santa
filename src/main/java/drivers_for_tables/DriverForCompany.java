package drivers_for_tables;

//import Records.CompanyRecords;

import pojo.Company;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class DriverForCompany {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private DatabaseMetaData dbmd;
    private ResultSetMetaData rsmd;
    private String urlToBase;
    private String baseName;
    private String user;
    private String password;


    public DriverForCompany(String urlToBase, String baseName, String user, String password) {
        this.urlToBase = urlToBase;
        this.baseName = baseName;
        this.user = user;
        this.password = password;
    }


    public boolean connectionToBase() {
        try {
            String connectionUrl = "jdbc:jtds:sqlserver://" + urlToBase + ";databaseName=" + baseName;
            conn = DriverManager.getConnection(connectionUrl, user, password);
            dbmd = conn.getMetaData();
            stm = conn.createStatement();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }

    }

    public Company readCompany(long id) throws SQLException {
        Company company;
        String sql = "SELECT id, name, description FROM people WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setLong(1, id);

        resultSet = preparedStatement.executeQuery();
        int resultId = resultSet.getInt("id");
        String resultName = resultSet.getString("name");
        String resultDescription = resultSet.getString("description");
        company = new Company(resultId, resultName, resultDescription);
        return company;
    }

    public List<Company> readCompanyList() throws SQLException {
        List<Company> company = new LinkedList<Company>();
            resultSet = stm.executeQuery("SELECT id, name, description FROM [company1]");
            rsmd = resultSet.getMetaData();
            if (rsmd != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    company.add(new Company(id, name, description));
                }
            }
        return company;
    }


    public void insertToTable(String name, String description){
        String sql = "INSERT INTO [company1] (name, description) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Company creation failed");
        }

    }

    public void deleteFromTable(Company company){
        String sql = "DELETE FROM [company1] WHERE id =  ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, company.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Deleting company is failed");
        }



    }

    public void updateInTable(Company company, String newName, String newDescription) throws SQLException {
        String sql = "UPDATE [company1] SET name= ?, description=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newDescription);
            preparedStatement.setLong(3, company.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Updating company is failed");
        }
    }

}







