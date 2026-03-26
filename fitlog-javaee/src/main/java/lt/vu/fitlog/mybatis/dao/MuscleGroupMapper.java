package lt.vu.fitlog.mybatis.dao;

import lt.vu.fitlog.mybatis.model.MuscleGroup;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface MuscleGroupMapper {

    @Select("SELECT ID, NAME FROM MUSCLEGROUP")
    List<MuscleGroup> selectAll();

    @Select("SELECT ID, NAME FROM MUSCLEGROUP WHERE ID = #{id}")
    MuscleGroup findOne(Long id);

    @Insert("INSERT INTO MUSCLEGROUP (NAME) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(MuscleGroup muscleGroup);
}