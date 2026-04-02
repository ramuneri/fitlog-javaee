package lt.vu.fitlog.mybatis.dao;

import lt.vu.fitlog.entities.Exercise;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface ExerciseMapper {

    @Select("SELECT ID, NAME, SETS, REPS, WORKOUTPLAN_ID AS workoutPlanId FROM EXERCISE")
    List<Exercise> selectAll();

    @Select("SELECT ID, NAME, SETS, REPS, WORKOUTPLAN_ID AS workoutPlanId FROM EXERCISE WHERE ID = #{id}")
    Exercise findOne(Long id);

    @Insert("INSERT INTO EXERCISE (NAME, SETS, REPS, WORKOUTPLAN_ID) " +
            "VALUES (#{name}, #{sets}, #{reps}, #{workoutPlan.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Exercise exercise);

    @Update("UPDATE EXERCISE SET NAME = #{name}, SETS = #{sets}, REPS = #{reps} WHERE ID = #{id}")
    int update(Exercise exercise);

    @Insert("INSERT INTO EXERCISE_MUSCLEGROUP (EXERCISES_ID, MUSCLEGROUPS_ID) " +
            "VALUES (#{exerciseId}, #{muscleGroupId})")
    int assignMuscleGroupToExercise(@Param("exerciseId") Long exerciseId,
                                    @Param("muscleGroupId") Long muscleGroupId);

    @Delete("DELETE FROM EXERCISE_MUSCLEGROUP WHERE EXERCISES_ID = #{exerciseId} AND MUSCLEGROUPS_ID = #{muscleGroupId}")
    int removeMuscleGroupFromExercise(@Param("exerciseId") Long exerciseId,
                                      @Param("muscleGroupId") Long muscleGroupId);

    @Select("SELECT MG.ID, MG.NAME " +
            "FROM MUSCLEGROUP MG " +
            "JOIN EXERCISE_MUSCLEGROUP EMG ON MG.ID = EMG.MUSCLEGROUPS_ID " +
            "WHERE EMG.EXERCISES_ID = #{exerciseId}")
    List<lt.vu.fitlog.entities.MuscleGroup> findMuscleGroupsByExerciseId(Long exerciseId);

    List<Exercise> selectAllWithGraph();
}
