package com.wicresoft.control;

import com.wicresoft.dao.EmployeeDao;
import com.wicresoft.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test1 {

    public static void main(String args[]){

        try {
            String resource="mybatis/mybatis-config.xml";
            /*获得当前文件的输入流*/
            Reader reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            /*获得sqlSession 对象*/
            /*设置参数为 true表示自动提交，如果是false, 则需要使用commit进行提交,默认为false*/
            SqlSession sqlSession=sqlSessionFactory.openSession(true);

//            Employee employee=sqlSession.selectOne("com.wicresoft.dao.EmployeeDao.selOne",2);
//            System.out.println(employee);

            /*创建接口对象, 这个是sqlSession对象通过动态代理自动帮我们创建的 Proxy代理*/
            EmployeeDao employeeDao=sqlSession.getMapper(EmployeeDao.class);

            /* Select By Step */
//            List<Employee> employees =employeeDao.getEmployeesByPagination(0,5);
//            System.out.println(employees);
//            System.out.println(employees);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
