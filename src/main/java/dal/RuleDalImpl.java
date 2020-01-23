package dal;

import application.Driver;
import pojo.Rule;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class RuleDalImpl implements RuleDal {
    private Driver driver = new Driver();

    @Override
    public void create(Rule rule) {

    }

    @Override
    public Rule read(int id) {
        return null;
    }

    @Override
    public void update(int id, Rule rule) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Rule> readList() {
        List<Rule> rule = new LinkedList<>();
        try {
            Statement st = driver.createStatement();
            String sql = "SELECT * FROM [rule]";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                int id = rs.getInt(1);
                int comp_id = rs.getInt(2);
                Date end_date = rs.getDate(3);
                int gift_price = rs.getInt(4);
                System.out.printf("%d, %d, %s, %d \n", id, comp_id, end_date, gift_price);
                rule.add(new Rule(id, comp_id, end_date, gift_price));
            }
            return rule;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
