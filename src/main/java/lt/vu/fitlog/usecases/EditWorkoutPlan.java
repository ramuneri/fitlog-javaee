package lt.vu.fitlog.usecases;

import lt.vu.fitlog.entities.WorkoutPlan;
import lt.vu.fitlog.persistence.WorkoutPlanDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Named
//@RequestScoped
@ViewScoped
public class EditWorkoutPlan implements Serializable {

    @Inject
    private WorkoutPlanDAO workoutPlanDAO;

    private WorkoutPlan workoutPlan;

    private Long id;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        System.out.println("EDIT BEAN INIT RUNNING");
        System.out.println("ID PARAM = " + params.get("id"));
        System.out.println("DAO CLASS = " + workoutPlanDAO.getClass().getName());

        if (params.get("id") != null) {
            id = Long.valueOf(params.get("id"));
            workoutPlan = workoutPlanDAO.findOne(id);
            System.out.println("FOUND PLAN = " + workoutPlan);
        }
    }

    @Transactional
    public String save() {
        try {
            workoutPlanDAO.update(workoutPlan);
            return "workoutPlans?faces-redirect=true";
        } catch (OptimisticLockException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "OptimisticLockException: someone else already updated this workout plan. Come back and try again.",
                            null));

            return null;
        }
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
    }

    public Long getId() {
        return id;
    }
}