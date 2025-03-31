package com.gym.dao;

import com.gym.model.WorkoutClass;
import com.gym.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutClassDAOImpl implements WorkoutClassDAO {
    @Override
    public WorkoutClass createClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "INSERT INTO workout_classes (class_name, description, schedule, trainer_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            pstmt.setString(1, workoutClass.getClassName());
            pstmt.setString(2, workoutClass.getDescription());
            pstmt.setTimestamp(3, workoutClass.getSchedule());
            pstmt.setInt(4, workoutClass.getTrainerId());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        workoutClass.setClassId(rs.getInt(1));
                    }
                }
            }
            return workoutClass;
        }
    }

    @Override
    public WorkoutClass getClassById(int classId) throws SQLException {
        String sql = "SELECT * FROM workout_classes WHERE class_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, classId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToWorkoutClass(rs);
            }
            return null;
        }
    }

    @Override
    public List<WorkoutClass> getClassesByTrainer(int trainerId) throws SQLException {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, trainerId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                classes.add(mapResultSetToWorkoutClass(rs));
            }
        }
        return classes;
    }

    @Override
    public List<WorkoutClass> getAllClasses() throws SQLException {
        List<WorkoutClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                classes.add(mapResultSetToWorkoutClass(rs));
            }
        }
        return classes;
    }

    @Override
    public boolean updateClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "UPDATE workout_classes SET class_name = ?, description = ?, schedule = ? WHERE class_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, workoutClass.getClassName());
            pstmt.setString(2, workoutClass.getDescription());
            pstmt.setTimestamp(3, workoutClass.getSchedule());
            pstmt.setInt(4, workoutClass.getClassId());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteClass(int classId) throws SQLException {
        String sql = "DELETE FROM workout_classes WHERE class_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, classId);
            return pstmt.executeUpdate() > 0;
        }
    }

    private WorkoutClass mapResultSetToWorkoutClass(ResultSet rs) throws SQLException {
        WorkoutClass workoutClass = new WorkoutClass();
        workoutClass.setClassId(rs.getInt("class_id"));
        workoutClass.setClassName(rs.getString("class_name"));
        workoutClass.setDescription(rs.getString("description"));
        workoutClass.setSchedule(rs.getTimestamp("schedule"));
        workoutClass.setTrainerId(rs.getInt("trainer_id"));
        return workoutClass;
    }
}