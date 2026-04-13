package lt.vu.fitlog.usecases;

import lt.vu.fitlog.entities.WorkoutPlan;
import lt.vu.fitlog.persistence.WorkoutPlanDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import org.mybatis.cdi.Transactional;
import javax.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
//@SessionScoped
//@ViewScoped
public class WorkoutPlans {

    @Inject
    private WorkoutPlanDAO workoutPlanDAO;

    private List<WorkoutPlan> allWorkoutPlans;

    private WorkoutPlan workoutPlanToCreate = new WorkoutPlan();

    @PostConstruct
    public void init() {
        allWorkoutPlans = workoutPlanDAO.loadAll();
    }

    @Transactional
    public String createWorkoutPlan() {
        workoutPlanDAO.persist(workoutPlanToCreate);
        return "workoutPlans?faces-redirect=true";
    }

    public List<WorkoutPlan> getAllWorkoutPlans() {
        return allWorkoutPlans;
    }

    public WorkoutPlan getWorkoutPlanToCreate() {
        return workoutPlanToCreate;
    }

    public void setWorkoutPlanToCreate(WorkoutPlan workoutPlanToCreate) {
        this.workoutPlanToCreate = workoutPlanToCreate;
    }
}
