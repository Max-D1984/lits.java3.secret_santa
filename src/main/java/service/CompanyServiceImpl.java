package service;

import dal.CompanyDal;
import dal.CompanyDalImpl;
import pojo.Company;
import java.util.List;


public class CompanyServiceImpl implements CompanyService {
    private CompanyDal companyDal = new CompanyDalImpl();

    @Override
    public void createCompany(Company company) {
        companyDal.create(company);
    }

    @Override
    public Company readCompany(long id) {
        return companyDal.read(id);
    }

    @Override
    public List<Company> readCompanyList() {
         return companyDal.readList();
    }

    @Override
    public void deleteCompany(Company company) {
        companyDal.delete(company);
    }

    @Override
    public void updateCompany(Company oldCompany, Company newCompany) {
       companyDal.update(oldCompany, newCompany);
    }

    @Override
    public void testCompany() {
        readCompanyList().stream().forEach(y -> System.out.println(y.getCompanyName()));
    }
}
