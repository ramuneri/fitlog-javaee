package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.MuscleGroup;

import java.util.List;

public interface MuscleGroupDAO {

    void persist(MuscleGroup muscleGroup);

    List<MuscleGroup> loadAll();

    MuscleGroup findOne(Long id);
}
