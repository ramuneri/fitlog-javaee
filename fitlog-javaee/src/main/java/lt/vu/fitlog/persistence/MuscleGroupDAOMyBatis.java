package lt.vu.fitlog.persistence;

import lt.vu.fitlog.entities.MuscleGroup;
import lt.vu.fitlog.mybatis.dao.MuscleGroupMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class MuscleGroupDAOMyBatis implements MuscleGroupDAO {

    @Inject
    private MuscleGroupMapper muscleGroupMapper;

    @Override
    public void persist(MuscleGroup muscleGroup) {
        muscleGroupMapper.insert(muscleGroup);
    }

    @Override
    public List<MuscleGroup> loadAll() {
        return muscleGroupMapper.selectAll();
    }

    @Override
    public MuscleGroup findOne(Long id) {
        return muscleGroupMapper.findOne(id);
    }
}