package lt.vu.fitlog.usecases;

import lt.vu.fitlog.entities.Exercise;
import lt.vu.fitlog.entities.WorkoutPlan;
import lt.vu.fitlog.persistence.ExerciseDAO;
import lt.vu.fitlog.persistence.WorkoutPlanDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@RequestScoped
public class Exercises {

    @Inject
    private ExerciseDAO exerciseDAO;

    @Inject
    private WorkoutPlanDAO workoutPlanDAO;

    private Exercise exerciseToCreate = new Exercise();

    private Long workoutPlanId;

    @Transactional
    public String createExercise() {
        WorkoutPlan workoutPlan = workoutPlanDAO.findOne(workoutPlanId);
        exerciseToCreate.setWorkoutPlan(workoutPlan);
        exerciseDAO.persist(exerciseToCreate);
        return "index?faces-redirect=true";
    }

    public Exercise getExerciseToCreate() {
        return exerciseToCreate;
    }

    public void setExerciseToCreate(Exercise exerciseToCreate) {
        this.exerciseToCreate = exerciseToCreate;
    }

    public Long getWorkoutPlanId() {
        return workoutPlanId;
    }

    public void setWorkoutPlanId(Long workoutPlanId) {
        this.workoutPlanId = workoutPlanId;
    }
}