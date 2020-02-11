package dal;

import application.Driver;
import pojo.Rule;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RuleDalImpl implements RuleDal {
    private static final String TABLE_NAME = "[rule]";
    private static final String TABLE_RULE_COLUMN_ID = "id";
    private static final String TABLE_RULE_COLUMN_COMPANY_ID = "company_" + TABLE_RULE_COLUMN_ID;
    private static final String TABLE_RULE_COLUMN_DESCRIPTION = "description";
    private static final String TABLE_RULE_COLUMN_END_DATE = "end_date";
    private static final String TABLE_RULE_COLUMN_GIFT_PRICE = "gift_price";
    private ResultSet resultSet;

    @Override
    public void create(Rule rule) {
    String sql = "INSERT INTO " + TABLE_NAME + " (" + TABLE_RULE_COLUMN_COMPANY_ID + ", " + TABLE_RULE_COLUMN_DESCRIPTION + ", " + TABLE_RULE_COLUMN_END_DATE + ", " + TABLE_RULE_COLUMN_GIFT_PRICE + ") VALUES(?,?,?,?)";
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
        String sql = "SELECT " + TABLE_RULE_COLUMN_ID + ", " + TABLE_RULE_COLUMN_COMPANY_ID
                + ", " + TABLE_RULE_COLUMN_DESCRIPTION + ", " + TABLE_RULE_COLUMN_END_DATE
                + ", " + TABLE_RULE_COLUMN_GIFT_PRICE + " FROM " + TABLE_NAME + " WHERE " + TABLE_RULE_COLUMN_ID + " = ?";
        try{
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int rule_id = resultSet.getInt(1);
                int comp_id = resultSet.getInt(2);
                String description = resultSet.getString(3);
                Date end_date = resultSet.getDate(4);
                int gift_price = resultSet.getInt(5);
                System.out.printf("%d, %d, %s, %s, %d \n", rule_id, comp_id, description, end_date, gift_price);
                rule = new Rule(rule_id,comp_id,description,end_date,gift_price);
            }
            return rule;
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, Rule rule) {
        String sql = "UPDATE " + TABLE_NAME + " SET " + TABLE_RULE_COLUMN_COMPANY_ID + "= ?, " + TABLE_RULE_COLUMN_DESCRIPTION + "=?, " + TABLE_RULE_COLUMN_END_DATE + "=?, " + TABLE_RULE_COLUMN_GIFT_PRICE + "=? WHERE " + TABLE_RULE_COLUMN_ID + "=?";
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
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + TABLE_RULE_COLUMN_ID + " =  ?";
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
            String sql = "SELECT " + TABLE_RULE_COLUMN_ID + ", " + TABLE_RULE_COLUMN_COMPANY_ID + ", " + TABLE_RULE_COLUMN_DESCRIPTION + ", " + TABLE_RULE_COLUMN_END_DATE + ", " + TABLE_RULE_COLUMN_GIFT_PRICE + " FROM " + TABLE_NAME;
            PreparedStatement preparedStatement = Driver.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int comp_id = resultSet.getInt(2);
                String description = resultSet.getString(3);
                Date end_date = resultSet.getDate(4);
                int gift_price = resultSet.getInt(5);
                System.out.printf("%d, %d, %s, %s, %d \n", id, comp_id, description, end_date, gift_price);
               rules.add(new Rule(id, comp_id, description, end_date, gift_price));
            }
        } catch (SQLException | IOException ex) {
            System.out.println("Wrong column name or table");
            System.out.println(ex);
        }
        return rules;
    }
}
