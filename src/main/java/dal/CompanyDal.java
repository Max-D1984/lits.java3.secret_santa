package dal;

import pojo.Company;

import java.util.List;

public interface CompanyDal {
    public List<Company> readList ();
    public Company read (long id);
    public void create (Company company);
    public void update (Company oldCompanyCompany, Company newCompany);
    public void delete (Company company);
    List<Company> getUsersCompany (List<Integer> user_id);
}
