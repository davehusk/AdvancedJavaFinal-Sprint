package com.gym.dao;

import com.gym.model.WorkoutClass;
import java.sql.SQLException;
import java.util.List;

public interface WorkoutClassDAO {
    WorkoutClass createClass(WorkoutClass workoutClass) throws SQLException;
    WorkoutClass getClassById(int classId) throws SQLException;
    List<WorkoutClass> getClassesByTrainer(int trainerId) throws SQLException;
    List<WorkoutClass> getAllClasses() throws SQLException;
    boolean updateClass(WorkoutClass workoutClass) throws SQLException;
    boolean deleteClass(int classId) throws SQLException;
}