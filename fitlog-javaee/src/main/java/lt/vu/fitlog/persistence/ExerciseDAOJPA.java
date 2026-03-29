package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.Exercise;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
@Default
public class ExerciseDAOJPA implements ExerciseDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Exercise exercise) {
        em.persist(exercise);
    }

    @Override
    public List<Exercise> loadAll() {
        return em.createQuery("select e from Exercise e", Exercise.class).getResultList();
    }

    @Override
    public Exercise findOne(Long id) {
        return em.find(Exercise.class, id);
    }
}