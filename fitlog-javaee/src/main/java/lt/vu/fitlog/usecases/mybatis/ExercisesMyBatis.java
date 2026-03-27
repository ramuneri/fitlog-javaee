package lt.vu.fitlog.usecases.mybatis;

import lt.vu.fitlog.mybatis.dao.ExerciseMapper;
import lt.vu.fitlog.mybatis.dao.MuscleGroupMapper;
import lt.vu.fitlog.mybatis.dao.WorkoutPlanMapper;
import lt.vu.fitlog.mybatis.model.Exercise;
import org.mybatis.cdi.Transactional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ExercisesMyBatis {

    @Inject
    private ExerciseMapper exerciseMapper;

    @Inject
    private WorkoutPlanMapper workoutPlanMapper;

    @Inject
    private MuscleGroupMapper muscleGroupMapper;

    private List<Exercise> allExercises;

    private Exercise exerciseToCreate = new Exercise();

    private Exercise exerciseToUpdate = new Exercise();

    private Long workoutPlanId;

    private Long exerciseIdForMuscleGroupAssignment;

    private Long muscleGroupIdForAssignment;

    @PostConstruct
    public void init() {
        allExercises = exerciseMapper.selectAll();

        for (Exercise exercise : allExercises) {
            exercise.setMuscleGroups(
                    exerciseMapper.findMuscleGroupsByExerciseId(exercise.getId())
            );
        }
    }

    @Transactional
    public String createExercise() {
        if (workoutPlanId == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Workout Plan ID is required.", null));
            return null;
        }

        if (workoutPlanMapper.findOne(workoutPlanId) == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Workout Plan ID does not exist.", null));
            return null;
        }

        exerciseToCreate.setWorkoutPlanId(workoutPlanId);
        exerciseMapper.insert(exerciseToCreate);

        return "/myBatis/exercises?faces-redirect=true";
    }

    @Transactional
    public String updateExercise() {
        if (exerciseToUpdate.getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exercise ID is required.", null));
            return null;
        }

        Exercise existingExercise = exerciseMapper.findOne(exerciseToUpdate.getId());

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

        exerciseMapper.update(existingExercise);
        return "/myBatis/exercises?faces-redirect=true";
    }

    @Transactional
    public String assignMuscleGroupToExercise() {
        if (exerciseIdForMuscleGroupAssignment == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exercise ID is required.", null));
            return null;
        }

        if (muscleGroupIdForAssignment == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Muscle Group ID is required.", null));
            return null;
        }

        Exercise existingExercise = exerciseMapper.findOne(exerciseIdForMuscleGroupAssignment);

        if (existingExercise == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exercise ID does not exist.", null));
            return null;
        }

        if (muscleGroupMapper.findOne(muscleGroupIdForAssignment) == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Muscle Group ID does not exist.", null));
            return null;
        }

        exerciseMapper.assignMuscleGroupToExercise(
                exerciseIdForMuscleGroupAssignment,
                muscleGroupIdForAssignment
        );

        return "/myBatis/exercises?faces-redirect=true";
    }

    public List<Exercise> getAllExercises() {
        return allExercises;
    }

    public void setAllExercises(List<Exercise> allExercises) {
        this.allExercises = allExercises;
    }

    public Exercise getExerciseToCreate() {
        return exerciseToCreate;
    }

    public void setExerciseToCreate(Exercise exerciseToCreate) {
        this.exerciseToCreate = exerciseToCreate;
    }

    public Exercise getExerciseToUpdate() {
        return exerciseToUpdate;
    }

    public void setExerciseToUpdate(Exercise exerciseToUpdate) {
        this.exerciseToUpdate = exerciseToUpdate;
    }

    public Long getWorkoutPlanId() {
        return workoutPlanId;
    }

    public void setWorkoutPlanId(Long workoutPlanId) {
        this.workoutPlanId = workoutPlanId;
    }

    public Long getExerciseIdForMuscleGroupAssignment() {
        return exerciseIdForMuscleGroupAssignment;
    }

    public void setExerciseIdForMuscleGroupAssignment(Long exerciseIdForMuscleGroupAssignment) {
        this.exerciseIdForMuscleGroupAssignment = exerciseIdForMuscleGroupAssignment;
    }

    public Long getMuscleGroupIdForAssignment() {
        return muscleGroupIdForAssignment;
    }

    public void setMuscleGroupIdForAssignment(Long muscleGroupIdForAssignment) {
        this.muscleGroupIdForAssignment = muscleGroupIdForAssignment;
    }
}