package dal;


import pojo.Company;

import java.util.List;

public interface CompanyDal {
     List<Company> readList ();
     Company read (long id);
     void create (Company company);
    void update (Company oldCompanyCompany, Company newCompany);
    void delete (Company company);
    List<Company> getUsersCompany (List<Integer> user_id);
}
