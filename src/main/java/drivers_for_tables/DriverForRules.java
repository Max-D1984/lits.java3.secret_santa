package drivers_for_tables;


import Records.RulesRecords;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DriverForRules {

    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private DatabaseMetaData dbmd;
    private ResultSetMetaData rsmd;
    private String urlToBase;
    private String baseName;
    private String user;
    private String password;


    public DriverForRules(String urlToBase, String baseName, String user, String password) {
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

    public List<RulesRecords> getDataFromTable() {
        List<RulesRecords> rules = new LinkedList();
        try {
            resultSet = stm.executeQuery("SELECT id, company_id, start_date, end_date, gift_date, gift_price FROM rules");
            rsmd = resultSet.getMetaData();
            if (rsmd != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int company_id = resultSet.getInt("company_id");
                    String start_date = resultSet.getString("start_date");
                    String end_date = resultSet.getString("end_date");
                    String gift_date = resultSet.getString("gift_date");
                    int gift_price = resultSet.getInt("gift_price");
                    rules.add(new RulesRecords(id, company_id, start_date, end_date, gift_date, gift_price));
                }
            }
        } catch (SQLException ex) {
            System.out.println("not right columns or table name");
        }
        return rules;
    }

    public void insertToTable(int company_id, String start_date, String end_date, String gift_date, int gift_price) throws SQLException {
        String str = "INSERT INTO rules (company_id, start_date, end_date, gift_date, gift_price)" +
                " VALUES ('" + company_id + "', '" + start_date + "','" + end_date + "','" + gift_date + "','" + gift_price + "')";
        stm.executeUpdate(str);
    }

    public void deleteFromTable(RulesRecords rules) throws SQLException{
        stm.executeUpdate("DELETE FROM Company WHERE id =  "+ rules.getId());
    }

    public void updateInTable(RulesRecords rules,int new_company_id, String new_start_date, String new_end_date, String new_gift_date, int new_gift_price)  throws SQLException {
        String str = "UPDATE rules SET company_id= '"+ new_company_id +"', start_date='"+ new_start_date + "'," +
                " end_date='"+ new_end_date + "', gift_date='" + new_gift_date + "', gift_price='" + new_gift_price +"' WHERE id="+rules.getId();
        stm.executeUpdate(str);
    }


}

