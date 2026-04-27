package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.Exercise;
import lt.vu.fitlog.entities.MuscleGroup;

import java.util.List;

public interface ExerciseDAO {

    void persist(Exercise exercise);

    void update(Exercise exercise);

    void assignMuscleGroupToExercise(Long exerciseId, Long muscleGroupId);

    List<Exercise> loadAll();

    Exercise findOne(Long id);

    List<MuscleGroup> findMuscleGroupsByExerciseId(Long exerciseId);
}
