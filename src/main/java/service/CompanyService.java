package service;

import pojo.Company;


public interface CompanyService {

    void createCompany(Company company);

    void readCompany();

    void readCompanyList();

    void deleteCompany();

    void updateCompany();

    void testCompany();
}
