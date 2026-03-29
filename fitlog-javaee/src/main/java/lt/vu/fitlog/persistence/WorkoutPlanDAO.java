package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.WorkoutPlan;

import java.util.List;

public interface WorkoutPlanDAO {

    void persist(WorkoutPlan workoutPlan);

    List<WorkoutPlan> loadAll();

    WorkoutPlan findOne(Long id);
}