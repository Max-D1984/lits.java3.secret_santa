package drivers_for_tables;

import Records.PresentRecords;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DriverForPresent {

    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private DatabaseMetaData dbmd;
    private ResultSetMetaData rsmd;
    private String urlToBase;
    private String baseName;
    private String user;
    private String password;

    //конструктор класу, використовується для отримання при створенні об'єкта url, baseName, user, password
    public DriverForPresent(String urlToBase, String baseName, String user, String password) {
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

    public List<PresentRecords> getDataFromTable() {
        List<PresentRecords> present = new LinkedList<PresentRecords>();
        try {
            resultSet = stm.executeQuery("SELECT id, Name, url FROM [Present]");
            rsmd = resultSet.getMetaData();
            if (rsmd != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("Name");
                    String url = resultSet.getString("url");
                    present.add(new PresentRecords(id, name, url));
                }
            }
        } catch (SQLException ex) {
            System.out.println("not right columns or table name");
        }
        return present;
    }

    public void insertToTable(String name, String url) throws SQLException {
        String str = "INSERT INTO Present (Name, url) VALUES ('" + name + "', '" + url + "')";
        stm.executeUpdate(str);
    }

    public void deleteFromTable(PresentRecords present) throws SQLException {
        stm.executeUpdate("DELETE FROM Present WHERE id =  " + present.getId());
    }

    public void updateInTable(PresentRecords present, String newName, String newUrl) throws SQLException {
        String str = "UPDATE [Present] SET Name = '" + newName + "', url ='" + newUrl + "' WHERE id=" + present.getId();
        stm.executeUpdate(str);
    }

}
