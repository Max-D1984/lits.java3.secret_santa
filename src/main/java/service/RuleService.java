package service;


import pojo.Rule;

public interface RuleService {
    void updateRule();

    void deleteRule();

    void createRule(Rule rule);

   void readRule();

   void readRuleList();
   void testRule();

}
