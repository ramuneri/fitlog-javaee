package lt.vu.fitlog.mybatis.dao;

import lt.vu.fitlog.mybatis.model.Exercise;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.cdi.Mapper;
import org.mybatis.cdi.SessionFactoryProvider;

import java.util.List;

@Mapper
public interface ExerciseMapper {

    @Select("SELECT ID, NAME, SETS, REPS, WORKOUTPLAN_ID AS workoutPlanId FROM EXERCISE")
    List<Exercise> selectAll();

    @Select("SELECT ID, NAME, SETS, REPS, WORKOUTPLAN_ID AS workoutPlanId FROM EXERCISE WHERE ID = #{id}")
    Exercise findOne(Long id);

    @Insert("INSERT INTO EXERCISE (NAME, SETS, REPS, WORKOUTPLAN_ID) " +
            "VALUES (#{name}, #{sets}, #{reps}, #{workoutPlanId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Exercise exercise);

    @Update("UPDATE EXERCISE SET NAME = #{name}, SETS = #{sets}, REPS = #{reps} WHERE ID = #{id}")
    int update(Exercise exercise);
}
