package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.WorkoutPlan;
import lt.vu.fitlog.mybatis.dao.WorkoutPlanMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class WorkoutPlanDAOMyBatis implements WorkoutPlanDAO {

    @Inject
    private WorkoutPlanMapper workoutPlanMapper;

    @Override
    public void persist(WorkoutPlan workoutPlan) {
        workoutPlanMapper.insert(workoutPlan);
    }

    @Override
    public List<WorkoutPlan> loadAll() {
        return workoutPlanMapper.selectAll();
    }

    @Override
    public WorkoutPlan findOne(Long id) {
        return workoutPlanMapper.findOne(id);
    }
}