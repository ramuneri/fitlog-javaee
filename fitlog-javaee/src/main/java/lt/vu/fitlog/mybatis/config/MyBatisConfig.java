package lt.vu.fitlog.mybatis.config;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

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

        configuration.addMapper(lt.vu.fitlog.mybatis.dao.ExerciseMapper.class);

        Environment environment = new Environment(
                "development",
                new JdbcTransactionFactory(),
                dataSource
        );

        configuration.setEnvironment(environment);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    @Produces
    public SqlSessionFactory produceSqlSessionFactory() {
        return sqlSessionFactory;
    }
}