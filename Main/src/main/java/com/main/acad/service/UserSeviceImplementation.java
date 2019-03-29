package com.main.acad.service;

import com.main.acad.dao.SimpleUserDao;
import com.main.acad.dao.UserDao;
import com.main.acad.entity.User;

import java.util.List;

public class UserSeviceImplementation implements UsersService {

    private UserDao dao = SimpleUserDao.getInstance();
    private static UserSeviceImplementation instance;

    private UserSeviceImplementation() {
    }

    public static UserSeviceImplementation getInstance() {
        if (instance == null) {
            instance = new UserSeviceImplementation();
        }
        return instance;
    }

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public User findByUser(String login, Integer password) {
        return dao.findUser(login,password);
    }

    @Override
    public boolean existUser(String userLogin) {
        return dao.existUser(userLogin);
    }

    @Override
    public boolean createNewUser(String login, Integer password, String role) {
        return dao.createNewUser(login,password,role);
    }
}
