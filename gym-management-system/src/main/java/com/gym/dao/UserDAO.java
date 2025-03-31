package com.gym.dao;

import com.gym.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User createUser(User user) throws SQLException;
    User getUserById(int userId) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    List<User> getUsersByRole(String role) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    boolean deleteUser(int userId) throws SQLException;
    boolean authenticateUser(String username, String password) throws SQLException;
}