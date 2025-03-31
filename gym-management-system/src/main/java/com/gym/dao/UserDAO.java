package com.gym.dao;

import java.sql.SQLException;
import java.util.List;

import com.gym.model.User;

public interface UserDAO {
    User createUser(User user) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    User authenticateUser(String username, String password) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    boolean deleteUser(int userId) throws SQLException;
    List<User> getUsersByRole(String role) throws SQLException;
}