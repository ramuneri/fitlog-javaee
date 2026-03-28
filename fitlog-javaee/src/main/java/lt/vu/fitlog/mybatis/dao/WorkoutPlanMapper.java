package lt.vu.fitlog.mybatis.dao;

import lt.vu.fitlog.mybatis.model.WorkoutPlan;
import lt.vu.fitlog.mybatis.model.Exercise;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface WorkoutPlanMapper {

    @Select("SELECT ID, NAME, DIFFICULTY FROM WORKOUTPLAN")
    List<WorkoutPlan> selectAll();

    @Select("SELECT ID, NAME, DIFFICULTY FROM WORKOUTPLAN WHERE ID = #{id}")
    WorkoutPlan findOne(Long id);

    @Insert("INSERT INTO WORKOUTPLAN (NAME, DIFFICULTY) VALUES (#{name}, #{difficulty})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(WorkoutPlan workoutPlan);

    @Select("SELECT ID, NAME, SETS, REPS, WORKOUTPLAN_ID AS workoutPlanId " +
            "FROM EXERCISE WHERE WORKOUTPLAN_ID = #{workoutPlanId}")
    List<Exercise> findExercisesByWorkoutPlanId(Long workoutPlanId);
}