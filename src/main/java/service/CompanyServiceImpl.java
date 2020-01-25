package service;

import dal.CompanyDal;
import dal.CompanyDalImpl;
import pojo.Company;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {
    @Override
    public void createCompany(Company company) {

    }

    @Override
    public Company readCompany(long id) {
        CompanyDal companyDal = new CompanyDalImpl();
        return companyDal.read(id);
    }

    @Override
    public List<Company> readCompanyList() {
        CompanyDal companyDal = new CompanyDalImpl();
        return companyDal.readList();
    }

    @Override
    public void deleteCompany(long id) {
        CompanyDal companyDal = new CompanyDalImpl();
        companyDal.delete(id);
    }

    @Override
    public void updateCompany(long id, Company company) {
        CompanyDal companyDal = new CompanyDalImpl();
        companyDal.update(id, company);
    }

    @Override
    public void testCompany() {

        readCompanyList().stream().forEach(y -> System.out.println(y.getCompanyName()));



    }
}
