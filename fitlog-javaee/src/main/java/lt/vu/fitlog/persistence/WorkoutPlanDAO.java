package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.WorkoutPlan;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class WorkoutPlanDAO {

    @PersistenceContext
    private EntityManager em;

    public void persist(WorkoutPlan workoutPlan) {
        em.persist(workoutPlan);
    }

    public List<WorkoutPlan> loadAll() {
        return em.createQuery("select w from WorkoutPlan w", WorkoutPlan.class).getResultList();
    }

    public WorkoutPlan findOne(Long id) {
        return em.find(WorkoutPlan.class, id);
    }

}
