package service;


import dal.RuleDal;
import dal.RuleDalImpl;
import pojo.Rule;
import java.util.List;

//@Service
public class RuleServiceImpl implements RuleService {
    private  RuleDal ruleDal = new RuleDalImpl();

    @Override
    public void update(int id, Rule rule) {
        ruleDal.update(id,rule);
    }

    @Override
    public void delete(int id) {
        ruleDal.delete(id);
    }

    @Override
    public void create(Rule rule) {
        ruleDal.create(rule);
    }

    @Override
    public Rule read(int id) {
        return ruleDal.read(id);
    }

    @Override
    public List<Rule> readList() {
        return ruleDal.readList();
    }

//    @Override
//    public void testRule() {
//        List<RulesRecords> rules;
//        DriverForRules drvRules = new DriverForRules("localhost:1433", "Santa", "sa", "sa");
//        if (drvRules.connectionToBase()) {
//            rule = drvRules.getDataFromTable();
//            rule.stream().forEach(y -> System.out.println(y.getId()));
//        }
//        try {
//            drvRules.insertToTable(2, "2019-12-05", "2019-12-20", "2019-12-26", 100);
//            rule = drvRules.getDataFromTable();
//        } catch (SQLException ex) {
//            System.out.println(ex);
//
//        }
//        try {
//            drvRules.updateInTable(rule.get(3), 5, "2019-12-15", "2019-12-19", "2019-12-24", 50);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//
//        }
//        try {
//            drvRules.deleteFromTable(rule.get(2));
//        } catch (SQLException ex) {
//            System.out.println(ex);
//
//        }
//        rules = drvRules.getDataFromTable();
//        rules.stream().forEach(y -> System.out.println(y.getId()));
//
//
//    }
}
