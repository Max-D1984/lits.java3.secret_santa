package service;


import dal.RuleDal;
import dal.RuleDalImpl;

import pojo.Rule;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RuleServiceImpl implements RuleService {

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

    }
}
