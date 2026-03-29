package lt.vu.fitlog.usecases.mybatis;

import lt.vu.fitlog.mybatis.dao.ExerciseMapper;
import lt.vu.fitlog.mybatis.dao.WorkoutPlanMapper;
import lt.vu.fitlog.entities.Exercise;
import lt.vu.fitlog.entities.WorkoutPlan;
import org.mybatis.cdi.Transactional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class WorkoutPlansMyBatis {

    @Inject
    private WorkoutPlanMapper workoutPlanMapper;

    @Inject
    private ExerciseMapper exerciseMapper;

    private List<WorkoutPlan> allWorkoutPlans;

    private WorkoutPlan workoutPlanToCreate = new WorkoutPlan();

    @PostConstruct
    public void init() {
        allWorkoutPlans = workoutPlanMapper.selectAll();

        for (WorkoutPlan plan : allWorkoutPlans) {
            List<Exercise> exercises = workoutPlanMapper.findExercisesByWorkoutPlanId(plan.getId());

            for (Exercise exercise : exercises) {
                exercise.setMuscleGroups(
                        exerciseMapper.findMuscleGroupsByExerciseId(exercise.getId())
                );
            }

            plan.setExercises(exercises);
        }
    }

    @Transactional
    public String createWorkoutPlan() {
        workoutPlanMapper.insert(workoutPlanToCreate);
        return "/myBatis/workoutPlans?faces-redirect=true";
    }

    public List<WorkoutPlan> getAllWorkoutPlans() {
        return allWorkoutPlans;
    }

    public void setAllWorkoutPlans(List<WorkoutPlan> allWorkoutPlans) {
        this.allWorkoutPlans = allWorkoutPlans;
    }

    public WorkoutPlan getWorkoutPlanToCreate() {
        return workoutPlanToCreate;
    }

    public void setWorkoutPlanToCreate(WorkoutPlan workoutPlanToCreate) {
        this.workoutPlanToCreate = workoutPlanToCreate;
    }
}