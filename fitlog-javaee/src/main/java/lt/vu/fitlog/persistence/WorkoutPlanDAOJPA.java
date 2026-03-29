package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.WorkoutPlan;

import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Default;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
//@Default
@Alternative
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
}