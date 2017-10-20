package dao;

import entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImp implements CompanyDao {

    @Autowired
    @Qualifier("hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Company getCompanyByName(String companyName) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Company where name= :name");
        query.setParameter("name", companyName);
        return (Company)query.uniqueResult();
    }

    public Long getCompanyIdByName(String companyName) {
        return getCompanyByName(companyName).getId();
    }
}
