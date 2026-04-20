package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.Exercise;
import lt.vu.fitlog.entities.WorkoutPlan;
import lt.vu.fitlog.mybatis.dao.WorkoutPlanMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Alternative
public class WorkoutPlanDAOMyBatis implements WorkoutPlanDAO {

    @Inject
    private WorkoutPlanMapper workoutPlanMapper;

    @Override
    public void persist(WorkoutPlan workoutPlan) {
        workoutPlanMapper.insert(workoutPlan);
    }

    @Override
    public List<WorkoutPlan> loadAll() {
        return workoutPlanMapper.selectAllWithGraph();
    }

    @Override
    public WorkoutPlan findOne(Long id) {
        return workoutPlanMapper.findOne(id);
    }

    @Override
    public List<Exercise> findExercisesByWorkoutPlanId(Long workoutPlanId) {
        return workoutPlanMapper.findExercisesByWorkoutPlanId(workoutPlanId);
    }

    @Override
    public WorkoutPlan update(WorkoutPlan workoutPlan) {
        workoutPlanMapper.update(workoutPlan);
        return workoutPlan;
    }
}
