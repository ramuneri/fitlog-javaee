package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.Exercise;
import lt.vu.fitlog.entities.MuscleGroup;

import javax.enterprise.inject.Alternative;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
//@Alternative
public class ExerciseDAOJPA implements ExerciseDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void persist(Exercise exercise) {
        em.persist(exercise);
    }

    @Override
    public void update(Exercise exercise) {
        em.merge(exercise);
    }

    @Override
    public void assignMuscleGroupToExercise(Long exerciseId, Long muscleGroupId) {
        Exercise exercise = em.find(Exercise.class, exerciseId);
        MuscleGroup muscleGroup = em.find(MuscleGroup.class, muscleGroupId);

        if (exercise != null && muscleGroup != null) {
            if (!exercise.getMuscleGroups().contains(muscleGroup)) {
                exercise.getMuscleGroups().add(muscleGroup);
            }
        }
    }

    @Override
    public List<Exercise> loadAll() {
        return em.createQuery("select e from Exercise e", Exercise.class).getResultList();
    }

    @Override
    public Exercise findOne(Long id) {
        return em.find(Exercise.class, id);
    }

    @Override
    public List<MuscleGroup> findMuscleGroupsByExerciseId(Long exerciseId) {
        Exercise exercise = em.find(Exercise.class, exerciseId);
        return exercise != null ? exercise.getMuscleGroups() : java.util.Collections.emptyList();
    }
}
