package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.Exercise;
import lt.vu.fitlog.entities.MuscleGroup;
import lt.vu.fitlog.mybatis.dao.ExerciseMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
//@Alternative
public class ExerciseDAOMyBatis implements ExerciseDAO {

    @Inject
    private ExerciseMapper exerciseMapper;

    @Override
    public void persist(Exercise exercise) {
        exerciseMapper.insert(exercise);
    }

    @Override
    public void update(Exercise exercise) {
        exerciseMapper.update(exercise);
    }

    @Override
    public void assignMuscleGroupToExercise(Long exerciseId, Long muscleGroupId) {
        exerciseMapper.assignMuscleGroupToExercise(exerciseId, muscleGroupId);
    }

    @Override
    public List<Exercise> loadAll() {
        return exerciseMapper.selectAllWithGraph();
    }

    @Override
    public Exercise findOne(Long id) {
        return exerciseMapper.findOne(id);
    }

    @Override
    public List<MuscleGroup> findMuscleGroupsByExerciseId(Long exerciseId) {
        return exerciseMapper.findMuscleGroupsByExerciseId(exerciseId);
    }
}
