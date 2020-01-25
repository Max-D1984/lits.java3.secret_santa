package service;

import pojo.Company;

import java.util.List;


public interface CompanyService {

    void createCompany(Company company);

    Company readCompany(long id);

    List<Company> readCompanyList();

    void deleteCompany(long id);

    void updateCompany(long id, Company company);

    void testCompany();
}
