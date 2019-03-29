package com.main.acad.controller;

import com.main.acad.annotation.MappingMethod;

import com.main.acad.entity.User;
import com.main.acad.error.ConnectionPoolFailedException;
import com.main.acad.error.ControllerFailedException;
import com.main.acad.error.UserDaoFailedException;
import com.main.acad.serializator.JsonSerializatorImplementation;
import com.main.acad.serializator.JsonSerializer;
import com.main.acad.service.UserSeviceImplementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class UsersConntroller {
    private static final Logger logger = Logger.getLogger(ChaptersController.class.getName());
    private UserSeviceImplementation userSeviceImplementation = UserSeviceImplementation.getInstance();

    @MappingMethod(url = "api/createNewUser")
    public void createNewUser(HttpServletRequest request, HttpServletResponse response) {
        boolean result = userSeviceImplementation.createNewUser(request.getParameter("login"),
                Integer.valueOf(request.getParameter("password")), "user");
        try {
            response.getWriter().write(String.valueOf(result));
        } catch (IOException | ConnectionPoolFailedException | UserDaoFailedException e) {
            logger.info("An error occurred in the ChaptersController in the createNewUser method" + e.getMessage());
            throw new ControllerFailedException(e.getMessage());
        }
    }

    @MappingMethod(url = "api/checkDateUser")
    public void checkDateUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (userSeviceImplementation.existUser(request.getParameter("login"))) {
                response.getWriter().write("this is free login");
            } else {
                response.getWriter().write("this is login exist");
            }
        } catch (IOException | ConnectionPoolFailedException | UserDaoFailedException e) {
            logger.info("An error occurred in the ChaptersController in the checkDateUser method" + e.getMessage());
            throw new ControllerFailedException(e.getMessage());
        }
    }

    @MappingMethod(url = "api/login")
    public void checkUser(HttpServletRequest request, HttpServletResponse response) {
        User userData = null;
        try {
            String jsonString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            JsonSerializer jsonSerializer = new JsonSerializatorImplementation();
            userData = (User) jsonSerializer.read(jsonString, User.class, null);
        } catch (Exception e) {
            throw new ControllerFailedException(e.getMessage());
        }
        User newUser = userSeviceImplementation.findByUser(userData.getLogin(), userData.getPassword());
        try {
            if (newUser == null) {
                response.getWriter().write("Your login or password have some error please write again");
            } else {
                if (newUser.getRole().trim().equals("admin")) {
                    response.getWriter().write("This is logged admin");
                } else if (newUser.getRole().trim().equals("user")) {
                    response.getWriter().write("This is logged user");
                }
            }
        } catch (IOException | ConnectionPoolFailedException | UserDaoFailedException e) {
            logger.info("An error occurred in the ChaptersController in the checkUser method" + e.getMessage());
            throw new ControllerFailedException(e.getMessage());
        }
    }
}
