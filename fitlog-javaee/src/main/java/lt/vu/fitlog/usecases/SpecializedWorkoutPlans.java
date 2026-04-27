package lt.vu.fitlog.usecases;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;

@RequestScoped
//@Specializes
public class SpecializedWorkoutPlans extends WorkoutPlans {

    @Override
    public String createWorkoutPlan() {
        System.out.println("USING SPECIALIZED WorkoutPlans");
        return super.createWorkoutPlan();
    }
}