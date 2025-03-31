package com.gym.service;

import com.gym.dao.WorkoutClassDAO;
import com.gym.model.WorkoutClass;
import java.sql.SQLException;
import java.util.List;

public class WorkoutClassService {
    private final WorkoutClassDAO workoutClassDAO;
    
    public WorkoutClassService(WorkoutClassDAO workoutClassDAO) {
        this.workoutClassDAO = workoutClassDAO;
    }
    
    public WorkoutClass createClass(String name, String description, 
                                  Timestamp schedule, int trainerId) throws SQLException {
        WorkoutClass workoutClass = new WorkoutClass();
        workoutClass.setClassName(name);
        workoutClass.setDescription(description);
        workoutClass.setSchedule(schedule);
        workoutClass.setTrainerId(trainerId);
        return workoutClassDAO.createClass(workoutClass);
    }
    
    public List<WorkoutClass> getTrainerClasses(int trainerId) throws SQLException {
        return workoutClassDAO.getClassesByTrainer(trainerId);
    }
    
    public List<WorkoutClass> getAllClasses() throws SQLException {
        return workoutClassDAO.getAllClasses();
    }
    
    public boolean updateClass(WorkoutClass workoutClass) throws SQLException {
        return workoutClassDAO.updateClass(workoutClass);
    }
    
    public boolean deleteClass(int classId) throws SQLException {
        return workoutClassDAO.deleteClass(classId);
    }
}