package com.main.acad.dao;

import com.main.acad.entity.User;
import com.main.acad.error.UserDaoFailedException;
import com.main.acad.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SimpleUserDao implements UserDao {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    private static final String FIND_ALL_USERS = "SELECT * FROM users ORDER BY login";
    private static final String FIND_BY_USER = "SELECT * FROM users WHERE login=? AND password=?";
    private static final String FIND_BY_USER_BY_LOGIN = "SELECT * FROM users WHERE login=? AND password=?";
    private static final String EXIT_USER = "SELECT * FROM users WHERE login =?";
    private static final String CREATE_USER = "INSERT INTO users( login, password, role) VALUES (?, ?, ?);";
    private static Connection connection;
    private static SimpleUserDao instance;

    private SimpleUserDao() {
    }

    public static SimpleUserDao getInstance() {
        if (instance == null) {
            instance = new SimpleUserDao();
        }
        return instance;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            connection = connectionPool.borrowConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                Integer password = resultSet.getInt("password");
                String role = resultSet.getString("role");
                /*User user = User._myuserbuilder()
                        .login(login)
                        .password(password)
                        .role(role)
                        .build();*/
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);
                users.add(user);
            }
        } catch (Exception e) {
            logger.info("An error occurred in the SimpleUserDao class in the findAllUsers method");
            throw new UserDaoFailedException(e.getMessage());
        } finally {
            connectionPool.surrenderConnection(connection);
        }
        logger.info("All users successfully find");
        return users;
    }

    @Override
    public User findUser(String login, Integer password) {
        try {
            connection = connectionPool.borrowConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                login = resultSet.getString("login");
                password = resultSet.getInt("password");
                String role = resultSet.getString("role");
                /*User user = User._myuserbuilder()
                        .login(login)
                        .password(password)
                        .role(role)
                        .build();*/
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);
                logger.info("All users successfully find");
                return user;
            } else {
                return null;
            }
        } catch (SQLException | InterruptedException e) {
            logger.info("An error occurred in the SimpleUserDao class in the findUser method");
            throw new UserDaoFailedException(e.getMessage());
        } finally {
            connectionPool.surrenderConnection(connection);
        }
    }

    @Override
    public boolean existUser(String userLogin) {
        try {
            connection = connectionPool.borrowConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EXIT_USER);
            preparedStatement.setString(1, userLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return false;
            logger.info(userLogin + "is exist");
        } catch (Exception e) {
            logger.info("An error occurred in the SimpleUserDao class in the exitUser method");
            throw new UserDaoFailedException(e.getMessage());
        } finally {
            connectionPool.surrenderConnection(connection);
        }
        return true;
    }

    @Override
    public boolean createNewUser(String login, Integer password, String role) {
        try {
            connection = connectionPool.borrowConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
            logger.info("New user successfully create");
            return true;
        } catch (Exception e) {
            logger.info("An error occurred in the SimpleUserDao class in the createNewUser method");
            throw new UserDaoFailedException(e.getMessage());
        } finally {
            connectionPool.surrenderConnection(connection);
        }
    }

    @Override
    public  String findUserByLogin(String login,String password) {
        try {
            connection = connectionPool.borrowConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, Integer.parseInt(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                login = resultSet.getString("login");
                password = resultSet.getString("password");
                String role = resultSet.getString("role");
                /*User user = User._myuserbuilder()
                        .login(login)
                        .password(password)
                        .role(role)
                        .build();*/
                User user = new User();
                user.setLogin(login);
                user.setPassword(Integer.parseInt(password));
                user.setRole(role);
                logger.info("User successfully find");
                return user.getRole();
            } else {
                logger.info("User not find");
                /*User user  = User._myuserbuilder()
                        .login(login)
                        .password(password)
                        .role("null")
                        .build();*/
                User user = new User();
                user.setLogin(login);
                user.setPassword(Integer.parseInt(password));
                user.setRole("null");
                return user.getRole();
            }
        } catch (SQLException | InterruptedException e) {
            logger.info("An error occurred in the SimpleUserDao class in the findUserByLogin method");
            throw new UserDaoFailedException(e.getMessage());
        } finally {
            connectionPool.surrenderConnection(connection);
        }
    }
}
