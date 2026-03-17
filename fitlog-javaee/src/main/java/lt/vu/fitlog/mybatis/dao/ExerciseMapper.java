package lt.vu.fitlog.mybatis.dao;

import lt.vu.fitlog.mybatis.model.Exercise;
import org.apache.ibatis.annotations.Select;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface ExerciseMapper {

    @Select("SELECT ID, NAME, SETS, REPS, WORKOUTPLAN_ID AS workoutPlanId FROM EXERCISE")
    List<Exercise> selectAll();
}