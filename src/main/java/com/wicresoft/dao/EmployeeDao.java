package com.wicresoft.dao;

import com.wicresoft.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeDao {
    /* Get Employee based on pagination in jQuery-easyui */
    public List<Employee> getEmployeesByPagination(@Param("pageFirst") Integer pageFirst, @Param("pageSize") Integer pageSize, @Param("Sort") String Sort, @Param("Order") String Order, @Param("queryStr") String queryStr);

    /* List All Employees */
    public List<Employee> getAllEmployees();

    /* Delete Employees Based on Ids*/
    public Integer deleteEmployees(String Ids[]);

    /* Add Employees*/
    public Integer addEmployees(Employee employee);

    /* Update Employees */
    public Integer updateEmployees(Employee employee);
}
