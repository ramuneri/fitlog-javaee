package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.Exercise;
import lt.vu.fitlog.entities.WorkoutPlan;

import javax.enterprise.inject.Alternative;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
//@Alternative
public class WorkoutPlanDAOJPA implements WorkoutPlanDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(WorkoutPlan workoutPlan) {
        em.persist(workoutPlan);
    }

    @Override
    public List<WorkoutPlan> loadAll() {
        return em.createQuery("select w from WorkoutPlan w", WorkoutPlan.class).getResultList();
    }

    @Override
    public WorkoutPlan findOne(Long id) {
        return em.find(WorkoutPlan.class, id);
    }

    @Override
    public List<Exercise> findExercisesByWorkoutPlanId(Long workoutPlanId) {
        WorkoutPlan workoutPlan = em.find(WorkoutPlan.class, workoutPlanId);
        return workoutPlan != null ? workoutPlan.getExercises() : java.util.Collections.emptyList();
    }

    @Override
    public WorkoutPlan update(WorkoutPlan workoutPlan) {
        WorkoutPlan merged = em.merge(workoutPlan);
        em.flush();
        return merged;
    }
}
