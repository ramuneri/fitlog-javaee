package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.MuscleGroup;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
@Default
public class MuscleGroupDAOJPA implements MuscleGroupDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(MuscleGroup muscleGroup) {
        em.persist(muscleGroup);
    }

    @Override
    public List<MuscleGroup> loadAll() {
        return em.createQuery("select m from MuscleGroup m", MuscleGroup.class).getResultList();
    }

    @Override
    public MuscleGroup findOne(Long id) {
        return em.find(MuscleGroup.class, id);
    }
}