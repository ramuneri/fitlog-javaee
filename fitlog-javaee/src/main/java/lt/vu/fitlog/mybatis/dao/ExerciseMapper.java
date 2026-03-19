package lt.vu.fitlog.mybatis.dao;

import lt.vu.fitlog.mybatis.model.Exercise;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface ExerciseMapper {

    @Select("SELECT ID, NAME, SETS, REPS, WORKOUTPLAN_ID AS workoutPlanId FROM EXERCISE")
    List<Exercise> selectAll();

    @Insert("INSERT INTO EXERCISE (NAME, SETS, REPS, WORKOUTPLAN_ID) " +
            "VALUES (#{name}, #{sets}, #{reps}, #{workoutPlanId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Exercise exercise);
}