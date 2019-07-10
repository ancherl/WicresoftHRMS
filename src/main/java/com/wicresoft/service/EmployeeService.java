package com.wicresoft.service;


import com.wicresoft.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;


public interface EmployeeService {

    /* List employees by pagination*/
    public List<Employee> getEmployeesByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr);

    /* List All employee */
    public List<Employee> getAllEmployees();

    /* Delete Employees based on Id
       Also available in Bulk delete
    * */
    public Integer deleteEmployees(String Ids[]);

    /*
    Add Employees
    * */
    public Integer addEmployees(Employee employee);

    /*
    Update Employees
    * */
    public Integer updateEmployees(Employee employee);


}
