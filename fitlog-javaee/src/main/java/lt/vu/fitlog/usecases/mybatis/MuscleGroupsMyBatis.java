package lt.vu.fitlog.usecases.mybatis;

import lt.vu.fitlog.mybatis.dao.MuscleGroupMapper;
import lt.vu.fitlog.mybatis.model.MuscleGroup;
import org.mybatis.cdi.Transactional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class MuscleGroupsMyBatis {

    @Inject
    private MuscleGroupMapper muscleGroupMapper;

    private List<MuscleGroup> allMuscleGroups;

    private MuscleGroup muscleGroupToCreate = new MuscleGroup();

    @PostConstruct
    public void init() {
        allMuscleGroups = muscleGroupMapper.selectAll();
    }

    @Transactional
    public String createMuscleGroup() {
        muscleGroupMapper.insert(muscleGroupToCreate);
        return "/myBatis/muscleGroups?faces-redirect=true";
    }

    public List<MuscleGroup> getAllMuscleGroups() {
        return allMuscleGroups;
    }

    public void setAllMuscleGroups(List<MuscleGroup> allMuscleGroups) {
        this.allMuscleGroups = allMuscleGroups;
    }

    public MuscleGroup getMuscleGroupToCreate() {
        return muscleGroupToCreate;
    }

    public void setMuscleGroupToCreate(MuscleGroup muscleGroupToCreate) {
        this.muscleGroupToCreate = muscleGroupToCreate;
    }
}