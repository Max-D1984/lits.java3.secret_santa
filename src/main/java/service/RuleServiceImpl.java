package service;

import Records.RulesRecords;
import dal.RuleDal;
import dal.RuleDalImpl;
import drivers_for_tables.DriverForRules;
import pojo.Rule;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RuleServiceImpl implements RuleService {
    static public List<RulesRecords> rule = new LinkedList<>();
    @Override
    public void updateRule() {

    }

    @Override
    public void deleteRule() {

    }

    @Override
    public void createRule(Rule rule) {

    }

    @Override
    public void readRule() {

    }

    @Override
    public void readRuleList() {
        RuleDal ruleDal = new RuleDalImpl();
        ruleDal.readList();

    }

    @Override
    public void testRule() {
        List<RulesRecords> rules;
        DriverForRules drvRules = new DriverForRules("localhost:1433", "Santa", "sa", "sa");
        if (drvRules.connectionToBase()) {
            rule = drvRules.getDataFromTable();
            rule.stream().forEach(y -> System.out.println(y.getId()));
        }
        try {
            drvRules.insertToTable(2, "2019-12-05", "2019-12-20", "2019-12-26", 100);
            rule = drvRules.getDataFromTable();
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        try {
            drvRules.updateInTable(rule.get(3), 5, "2019-12-15", "2019-12-19", "2019-12-24", 50);
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        try {
            drvRules.deleteFromTable(rule.get(2));
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        rules = drvRules.getDataFromTable();
        rules.stream().forEach(y -> System.out.println(y.getId()));


    }
}
