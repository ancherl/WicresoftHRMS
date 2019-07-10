package com.wicresoft.serviceImpl;

import com.wicresoft.dao.EmployeeDao;
import com.wicresoft.entity.Employee;
import com.wicresoft.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    /*  @Resource 注解属于J2EE 按名称装配
     *  @Autowired 注解属于Srping 按类型装配
     *  */

    @Resource
    private EmployeeDao employeeDao;


    @Override
    public List<Employee> getEmployeesByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr) {
        return employeeDao.getEmployeesByPagination(pageFirst,pageSize, Sort, Order, queryStr);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Integer deleteEmployees(String Ids[]) {
        return employeeDao.deleteEmployees(Ids);
    }

    @Override
    public Integer addEmployees(Employee employee) {

        return employeeDao.addEmployees(employee);
    }

    @Override
    public Integer updateEmployees(Employee employee) {
        return employeeDao.updateEmployees(employee);
    }


}
