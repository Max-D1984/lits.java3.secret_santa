package dal;

import pojo.Company;

import java.util.List;

public interface CompanyDal {
    public void create (Company company);
    public Company read (long id);
    public void update (long id, Company company);
    public void delete (long id);
    public List<Company> readList ();
}
