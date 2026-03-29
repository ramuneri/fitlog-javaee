package lt.vu.fitlog.usecases;

import lt.vu.fitlog.entities.Exercise;
import lt.vu.fitlog.entities.MuscleGroup;
import lt.vu.fitlog.entities.WorkoutPlan;
import lt.vu.fitlog.persistence.ExerciseDAO;
import lt.vu.fitlog.persistence.MuscleGroupDAO;
import lt.vu.fitlog.persistence.WorkoutPlanDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.mybatis.cdi.Transactional;
import java.util.List;

@Named
@RequestScoped
public class Exercises {

    @Inject
    private ExerciseDAO exerciseDAO;

    @Inject
    private WorkoutPlanDAO workoutPlanDAO;

    @Inject
    private MuscleGroupDAO muscleGroupDAO;

    private List<Exercise> allExercises;

    private Exercise exerciseToCreate = new Exercise();

    private Exercise exerciseToUpdate = new Exercise();

    private Long workoutPlanId;
    private Long exerciseId;
    private Long muscleGroupId;

    @PostConstruct
    public void init() {
        allExercises = exerciseDAO.loadAll();

        for (Exercise exercise : allExercises) {
            exercise.setMuscleGroups(
                    exerciseDAO.findMuscleGroupsByExerciseId(exercise.getId())
            );
        }
    }

    @Transactional
    public String createExercise() {
        WorkoutPlan workoutPlan = workoutPlanDAO.findOne(workoutPlanId);

        if (workoutPlan == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Workout Plan ID does not exist.", null));
            return null;
        }

        exerciseToCreate.setWorkoutPlan(workoutPlan);
        exerciseDAO.persist(exerciseToCreate);
        return "exercises?faces-redirect=true";
    }

    @Transactional
    public String assignMuscleGroupToExercise() {
        Exercise exercise = exerciseDAO.findOne(exerciseId);
        MuscleGroup muscleGroup = muscleGroupDAO.findOne(muscleGroupId);

        if (exercise == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exercise ID does not exist.", null));
            return null;
        }

        if (muscleGroup == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Muscle Group ID does not exist.", null));
            return null;
        }

        exerciseDAO.assignMuscleGroupToExercise(exerciseId, muscleGroupId);

        return "exercises?faces-redirect=true";
    }


    @Transactional
    public String updateExercise() {
        if (exerciseToUpdate.getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exercise ID is required.", null));
            return null;
        }

        Exercise existingExercise = exerciseDAO.findOne(exerciseToUpdate.getId());

        if (existingExercise == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exercise with this ID does not exist.", null));
            return null;
        }

        if (exerciseToUpdate.getName() != null && !exerciseToUpdate.getName().trim().isEmpty()) {
            existingExercise.setName(exerciseToUpdate.getName());
        }

        if (exerciseToUpdate.getSets() != null) {
            existingExercise.setSets(exerciseToUpdate.getSets());
        }

        if (exerciseToUpdate.getReps() != null) {
            existingExercise.setReps(exerciseToUpdate.getReps());
        }

        exerciseDAO.update(existingExercise);

        return "/exercises?faces-redirect=true";
    }


    public List<Exercise> getAllExercises() {
        return allExercises;
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

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Long getMuscleGroupId() {
        return muscleGroupId;
    }

    public void setMuscleGroupId(Long muscleGroupId) {
        this.muscleGroupId = muscleGroupId;
    }

    public Exercise getExerciseToUpdate() {
        return exerciseToUpdate;
    }

    public void setExerciseToUpdate(Exercise exerciseToUpdate) {
        this.exerciseToUpdate = exerciseToUpdate;
    }
}