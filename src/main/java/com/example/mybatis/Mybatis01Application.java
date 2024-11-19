package com.example.mybatis;

import com.example.mybatis.model.DeptTO;
import com.example.mybatis.model.EmpTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class Mybatis01Application implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ApplicationContext ctx;

    public static void main(String[] args) {
        SpringApplication.run(Mybatis01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Mybatis Framwork 접근
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(ctx.getResource("classpath:/mapper/mapper2.xml"));

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        System.out.println(sqlSession);

//        String result = sqlSession.selectOne("select");
//
//        System.out.println("현재 시간: " + result);

//        Map map = sqlSession.selectOne("selectone1");
//        System.out.println(map.size());
//
//        Set<String> keys = map.keySet();
//        for (String key : keys) {
//            System.out.println(key + " : " + map.get(key));
//        }

//        List<Map<String, Object>> lists = sqlSession.selectList("selectlist1");
//        System.out.println(lists.size()); //데이터갯수
//
//        for (Map<String, Object> map : lists) {
//           // System.out.println(map.size()); //map갯수 열의 갯수
//            System.out.println(map.get("deptno") + "/" + map.get("dname") + "/" + map.get("loc"));
//        }

//        DeptTO to  = sqlSession.selectOne("selectone");
//        System.out.println(to.getDeptno() + "/" + to.getDname() + "/" + to.getLoc());

//        List<DeptTO> list =sqlSession.selectList("selectlist1");
//
//        for(DeptTO to : list) {
//            System.out.println(to.getDeptno() + "/" + to.getDname() + "/" + to.getLoc());
//        }
//        List<EmpTO> list =sqlSession.selectList("selectlist2", "10");
        List<EmpTO> list =sqlSession.selectList("selectlist3", "S%");

        for (EmpTO to : list) {
            System.out.println(to.getEmpno() + "/" + to.getEname() + "/" + to.getDeptno());
        }

        sqlSession.close();
    }
}
