package lt.vu.fitlog.usecases;

import lt.vu.fitlog.entities.MuscleGroup;
import lt.vu.fitlog.persistence.MuscleGroupDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.mybatis.cdi.Transactional;
import java.util.List;

@Named
@RequestScoped
public class MuscleGroups {

    @Inject
    private MuscleGroupDAO muscleGroupDAO;

    private List<MuscleGroup> allMuscleGroups;

    private MuscleGroup muscleGroupToCreate = new MuscleGroup();

    @PostConstruct
    public void init() {
        allMuscleGroups = muscleGroupDAO.loadAll();
    }

    @Transactional
    public String createMuscleGroup() {
        muscleGroupDAO.persist(muscleGroupToCreate);
        return "muscleGroups?faces-redirect=true";
    }

    public List<MuscleGroup> getAllMuscleGroups() {
        return allMuscleGroups;
    }

    public MuscleGroup getMuscleGroupToCreate() {
        return muscleGroupToCreate;
    }

    public void setMuscleGroupToCreate(MuscleGroup muscleGroupToCreate) {
        this.muscleGroupToCreate = muscleGroupToCreate;
    }
}