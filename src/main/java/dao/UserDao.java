package dao;


import entity.User;

import java.util.List;

public interface UserDao {
    void save(User userDao);

    void deleteUser(User userDao);

    User getUserByNameUnique(String name);

    int deleteUserByName(String name);

    List<User> getUserByNameList(String name);

    List<User> getUserList();
}