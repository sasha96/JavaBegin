package com.main.acad.dao;

import com.main.acad.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();

    User findUser(String login, Integer password);

    boolean existUser(String userLogin);

    boolean createNewUser(String login, Integer password, String role);

    String findUserByLogin(String login,String password);
}
