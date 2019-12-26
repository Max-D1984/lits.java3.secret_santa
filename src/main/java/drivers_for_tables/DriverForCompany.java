package drivers_for_tables;

import Records.CompanyRecords;

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
        boolean connection;
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

    public List<CompanyRecords> getDataFromTable() {
        List<CompanyRecords> company = new LinkedList<CompanyRecords>();
        try {
            resultSet = stm.executeQuery("SELECT id, Name_Company, Description_Company FROM Company");
            rsmd = resultSet.getMetaData();
            if (rsmd != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("Name_Company");
                    String description = resultSet.getString("Description_Company");
                    company.add(new CompanyRecords(id, name, description));
                }
            }
        } catch (SQLException ex) {
            System.out.println("not right columns or table name");
        }
        return company;
    }

    public void insertToTable(String name, String description) throws SQLException {
        String str = "INSERT INTO Company (Name_Company, Description_Company) VALUES ('" + name + "', '" + description + "')";
        stm.executeUpdate(str);
    }

    public void deleteFromTable(CompanyRecords company) throws SQLException{
        stm.executeUpdate("DELETE FROM Company WHERE id =  "+ company.getId());

    }

    public void updateInTable(CompanyRecords company, String newName, String newDescription)  throws SQLException {
        String str = "UPDATE Company SET Name_Company= '"+newName+"', Description_Company='"+ newDescription+"' WHERE id="+company.getId();
        stm.executeUpdate(str);
    }

}







