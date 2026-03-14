package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.Exercise;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ExerciseDAO {

    @PersistenceContext
    private EntityManager em;

    public void persist(Exercise exercise) {
        em.persist(exercise);
    }

    public List<Exercise> loadAll() {
        return em.createQuery("select e from Exercise e", Exercise.class).getResultList();
    }
}