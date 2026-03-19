package lt.vu.fitlog.usecases.mybatis;

import lt.vu.fitlog.mybatis.dao.ExerciseMapper;
import lt.vu.fitlog.mybatis.model.Exercise;
import lt.vu.fitlog.persistence.WorkoutPlanDAO;
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
    private WorkoutPlanDAO workoutPlanDAO;

    private List<Exercise> allExercises;

    private Exercise exerciseToCreate = new Exercise();

    private Long workoutPlanId;

    @PostConstruct
    public void init() {
        allExercises = exerciseMapper.selectAll();
    }

    @Transactional
    public String createExercise() {
        if (workoutPlanDAO.findOne(workoutPlanId) == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Workout Plan ID does not exist.", null));
            return null;
        }

        exerciseToCreate.setWorkoutPlanId(workoutPlanId);
        exerciseMapper.insert(exerciseToCreate);

        return "/myBatis/exercises?faces-redirect=true";
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
}