package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.MuscleGroup;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MuscleGroupDAO {

    @PersistenceContext
    private EntityManager em;

    public void persist(MuscleGroup muscleGroup) {
        em.persist(muscleGroup);
    }

    public List<MuscleGroup> loadAll() {
        return em.createQuery("select m from MuscleGroup m", MuscleGroup.class).getResultList();
    }
}