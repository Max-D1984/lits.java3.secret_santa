package dal;

import application.Driver;
import pojo.Rule;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RuleDalImpl implements RuleDal {
    private ResultSet resultSet;

    @Override
    public void create(Rule rule) {
    String sql = "INSERT INTO [rule] (company_id, description, end_date, gift_price) VALUES(?,?,?,?)";
    try{
        PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,rule.getCompany_id());
        preparedStatement.setString(2,rule.getDescription());
        preparedStatement.setDate(3,rule.getEnd_date());
        preparedStatement.setInt(4,rule.getGift_price());
        preparedStatement.executeUpdate();
    } catch (SQLException | IOException ex) {
        ex.printStackTrace();
    }
    }

    @Override
    public Rule read(int id) {
        Rule rule = null;
        String sql = "SELECT r.id,c.name,r.description,r.end_date,r.gift_price\n" +
                "                    FROM [rule] r \n" +
                "                    JOIN [company1] c ON r.company_id=c.id WHERE r.id = ?";
        try{
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int rule_id = resultSet.getInt(1);
                String comp_id = resultSet.getString(2);
                String description = resultSet.getString(3);
                Date end_date = resultSet.getDate(4);
                int gift_price = resultSet.getInt(5);
                System.out.printf("%d, %s, %s, %s, %d \n", rule_id, comp_id, description, end_date, gift_price);
                rule = new Rule(rule_id,rule_id,description,end_date,gift_price);
            }
            return rule;
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, Rule rule) {
        String sql = "UPDATE [rule] SET company_id= ?, description=?, end_date=?, gift_price=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,rule.getCompany_id());
            preparedStatement.setString(2, rule.getDescription());
            preparedStatement.setDate(3, rule.getEnd_date());
            preparedStatement.setInt(4,rule.getGift_price());
            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            System.out.println("Updating rule is failed");
            System.out.println(ex);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM [rule] WHERE id =  ?";
        try {
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException ex) {
            System.out.println("Deleting rule is failed");
            System.out.println(ex);
        }
    }

    @Override
    public List<Rule> readList() {
        List<Rule> rules = new LinkedList<>();
        try {
            String sql = "SELECT r.id,c.name,r.description,r.end_date,r.gift_price\n" +
                    "FROM [rule] r \n" +
                    "JOIN [company1] c ON r.company_id=c.id";
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String comp_id = resultSet.getString(2);
                String description = resultSet.getString(3);
                Date end_date = resultSet.getDate(4);
                int gift_price = resultSet.getInt(5);
                System.out.printf("%d, %s, %s, %s, %d \n", id, comp_id, description, end_date, gift_price);
               rules.add(new Rule(id, id, description, end_date, gift_price));
            }
        } catch (SQLException | IOException ex) {
            System.out.println("Wrong column name or table");
            System.out.println(ex);
        }
        return rules;
    }
}
