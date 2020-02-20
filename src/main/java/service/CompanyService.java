package service;

import pojo.Company;

import java.util.List;


public interface CompanyService {

    void createCompany(Company company);

    Company readCompany(long id);

    List<Company> readCompanyList();

    void deleteCompany(Company company);

    void updateCompany(Company newCompany, Company oldCompany);

    void testCompany();
    List<Company> getUsersCompany (List<Integer> user_id);
}
