package lt.vu.fitlog.mybatis.config;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.cdi.SessionFactoryProvider;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import java.io.InputStream;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class MyBatisConfig {

    @Resource(lookup = "java:jboss/datasources/FitLogDS")
    private DataSource dataSource;

    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init() {
        org.apache.ibatis.session.Configuration configuration =
                new org.apache.ibatis.session.Configuration();

        configuration.addMapper(lt.vu.fitlog.mybatis.dao.MuscleGroupMapper.class);

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("lt/vu/fitlog/mybatis/dao/ExerciseMapper.xml")) {

            if (inputStream == null) {
                throw new RuntimeException("ExerciseMapper.xml not found");
            }

            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(
                    inputStream,
                    configuration,
                    "lt/vu/fitlog/mybatis/dao/ExerciseMapper.xml",
                    configuration.getSqlFragments()
            );

            xmlMapperBuilder.parse();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load ExerciseMapper.xml", e);
        }

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("lt/vu/fitlog/mybatis/dao/WorkoutPlanMapper.xml")) {

            if (inputStream == null) {
                throw new RuntimeException("WorkoutPlanMapper.xml not found");
            }

            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(
                    inputStream,
                    configuration,
                    "lt/vu/fitlog/mybatis/dao/WorkoutPlanMapper.xml",
                    configuration.getSqlFragments()
            );

            xmlMapperBuilder.parse();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load WorkoutPlanMapper.xml", e);
        }


        Environment environment = new Environment(
                "development",
                new JdbcTransactionFactory(),
                dataSource
        );

        configuration.setEnvironment(environment);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    public SqlSessionFactory produceSqlSessionFactory() {
        return sqlSessionFactory;
    }
}