package dal;

import pojo.Rule;

import java.util.List;

public interface RuleDal {
     void create (Rule rule);
     Rule read (int id);
     void update (int id, Rule rule);
     void delete (int id);
     List<Rule> readList ();
}
