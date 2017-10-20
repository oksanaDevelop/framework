package dao;


import entity.Company;

public interface CompanyDao {
    Company getCompanyByName(String companyName);
     Long getCompanyIdByName(String companyName) ;
}
