package service;

import pojo.Rule;

import java.util.List;

public interface RuleService {
    void update (int id, Rule rule);
    void delete (int id);
    void create (Rule rule);
    Rule read (int id);
    List<Rule> readList ();
  // void testRule();

}
