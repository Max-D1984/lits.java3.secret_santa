
package drivers_for_tables;


        import Records.UserRecords;

        import java.sql.*;
        import java.util.LinkedList;
        import java.util.List;

public class DriverForUser {
    private Connection conn; //обявляємо об'єкт зє'днання з базою
    private Statement stm; // об'єкт для виконання запитів з бази даних
    private ResultSet resultSet; //об'єкт для запису результату виконання запитів з бази даних
    private DatabaseMetaData dbmd; //  об'єкт для отримання метаданих бази
    private ResultSetMetaData rsmd; // об'єкт для отримання метаданих результату
    private String urlToBase; // стрічка з даними про місцезнаходження бази
    private String baseName; // стрічка з назвою бази
    private String user;
    private String password;

    //конструктор класу, використовується для отримання при створенні об'єкта url, baseName, user, password
    public DriverForUser(String urlToBase, String baseName, String user, String password) {
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
    public List<UserRecords> getDataFromTable() {
        List<UserRecords> user = new LinkedList<UserRecords>();
        try {
            resultSet = stm.executeQuery("SELECT id, Name, Role FROM [User]");
            rsmd = resultSet.getMetaData();
            if (rsmd != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("Name");
                    String role = resultSet.getString("Role");
                    user.add(new UserRecords(id, name, role));
                }

            }
        } catch (SQLException ex) {
            System.out.println("not right columns or table name");
        }
        return user;
    }
    public void insertToTable(String name, String role) throws SQLException {
        String str = "INSERT INTO [User] (Name, Role) VALUES ('" + name + "', '" + role + "')";
        stm.executeUpdate(str);
    }

    public void deleteFromTable(UserRecords user) throws SQLException{
        stm.executeUpdate("DELETE FROM [User] WHERE id =  "+ user.getId());

    }

    public void updateInTable(UserRecords user, String newName, String newRole)  throws SQLException {
        String str = "UPDATE [User] SET Name = '"+newName+"', Role ='"+ newRole+"' WHERE id="+user.getId();
        stm.executeUpdate(str);
    }

}



