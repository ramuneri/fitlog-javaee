package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.Exercise;

import java.util.List;

public interface ExerciseDAO {

    void persist(Exercise exercise);

    List<Exercise> loadAll();

    Exercise findOne(Long id);
}