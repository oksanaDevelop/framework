package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.hibernate.query.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    @Qualifier("hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public void save(User userDao) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println(userDao);
        session.saveOrUpdate(userDao);
        tx.commit();
        session.close();
    }

    @Override
    public void deleteUser(User userDao) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(userDao);
        tx.commit();
        session.close();
    }

    @Override
    @Transactional
    public int deleteUserByName(String name) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("delete User where name= :name");
        Transaction tx = session.beginTransaction();
        query.setParameter("name", name);
        int res = query.executeUpdate();
        tx.commit();
        session.close();
        return res;
    }

    @Override
    public User getUserByNameUnique(String name) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from User where name= :name");
        query.setParameter("name", name);
        return (User) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserByNameList(String name) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from User where name= :name");
        query.setParameter("name", name);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserList() {
        Session session = this.sessionFactory.openSession();
        List<User> userList = session.createQuery("from User").list();
        session.close();
        return userList;
    }

}