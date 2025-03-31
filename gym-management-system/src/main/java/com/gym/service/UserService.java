package com.gym.service;

import com.gym.dao.UserDAO;
import com.gym.model.User;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;
    
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    public User registerUser(String username, String password, String email,
                           String phoneNumber, String address, String role) throws SQLException {
        User user;
        switch (role.toUpperCase()) {
            case "ADMIN":
                user = new Admin(username, password, email, phoneNumber, address);
                break;
            case "TRAINER":
                user = new Trainer(username, password, email, phoneNumber, address);
                break;
            default:
                user = new Member(username, password, email, phoneNumber, address);
        }
        return userDAO.createUser(user);
    }
    
    public User loginUser(String username, String password) throws SQLException {
        if (userDAO.authenticateUser(username, password)) {
            return userDAO.getUserByUsername(username);
        }
        return null;
    }
    
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }
    
    public List<User> getUsersByRole(String role) throws SQLException {
        return userDAO.getUsersByRole(role);
    }
    
    public boolean updateUser(User user) throws SQLException {
        return userDAO.updateUser(user);
    }
    
    public boolean deleteUser(int userId) throws SQLException {
        return userDAO.deleteUser(userId);
    }
    
    public User getUserById(int userId) throws SQLException {
        return userDAO.getUserById(userId);
    }
    
    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }
}