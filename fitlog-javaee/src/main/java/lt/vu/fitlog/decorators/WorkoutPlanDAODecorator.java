package lt.vu.fitlog.decorators;

import lt.vu.fitlog.entities.Exercise;
import lt.vu.fitlog.entities.WorkoutPlan;
import lt.vu.fitlog.persistence.WorkoutPlanDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.util.List;

@Decorator
public abstract class WorkoutPlanDAODecorator implements WorkoutPlanDAO {

    @Inject
    @Delegate
    private WorkoutPlanDAO workoutPlanDAO;

    @Override
    public void persist(WorkoutPlan workoutPlan) {
        System.out.println("Saving workout plan... (DECORATOR): " + workoutPlan.getName());
        workoutPlanDAO.persist(workoutPlan);
        System.out.println("Saved successfully! (DECORATOR)");
    }

    @Override
    public List<WorkoutPlan> loadAll() {
        System.out.println("DECORATOR: loading all workout plans yappy (DECORATOR)");
        return workoutPlanDAO.loadAll();
    }

    @Override
    public WorkoutPlan findOne(Long id) {
        return workoutPlanDAO.findOne(id);
    }

    @Override
    public List<Exercise> findExercisesByWorkoutPlanId(Long workoutPlanId) {
        return workoutPlanDAO.findExercisesByWorkoutPlanId(workoutPlanId);
    }

    @Override
    public WorkoutPlan update(WorkoutPlan workoutPlan) {
        return workoutPlanDAO.update(workoutPlan);
    }
}