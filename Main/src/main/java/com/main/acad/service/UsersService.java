package com.main.acad.service;

import com.main.acad.entity.User;

import java.util.List;

public interface UsersService {
    List<User> findAllUsers();

    User findByUser(String login, Integer password);

    boolean existUser(String userLogin);

    boolean createNewUser(String login, Integer password, String role);
}
