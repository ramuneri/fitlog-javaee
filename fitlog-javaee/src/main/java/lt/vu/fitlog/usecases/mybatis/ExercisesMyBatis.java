package lt.vu.fitlog.usecases.mybatis;

import lt.vu.fitlog.mybatis.dao.ExerciseMapper;
import lt.vu.fitlog.mybatis.model.Exercise;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ExercisesMyBatis {

    @Inject
    private ExerciseMapper exerciseMapper;

    private List<Exercise> allExercises;

    @PostConstruct
    public void init() {
        allExercises = exerciseMapper.selectAll();
    }

    public List<Exercise> getAllExercises() {
        return allExercises;
    }
}