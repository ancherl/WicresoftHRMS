package com.wicresoft.dao;

import com.wicresoft.entity.*;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AdminDao {

    /* Check if Admin account is existed*/
    public Admin checkAdminAccount(@Param("account") String account);

    /* List All Menu Options*/
    public List<AdminMenuOption> getMenuOptions(@Param("child_id") Integer child_id);

    /* List Employees By Pagination*/
    public List<Employee> listAllEmployeesByPagination(@Param("pageFirst") Integer pageFirst, @Param("pageSize") Integer pageSize, @Param("Sort") String Sort, @Param("Order") String Order, @Param("queryStr") String queryStr);

    /* List All Employees */
    public List<Employee> listAllEmployees();

    /* Add new Employee*/
    public Integer addEmployee(Employee employee);

    /* Add a new Address */
    public Integer addAddress(Address address);

    /* Search All Addresses*/
    public List<Address> listAllAddresses();

    /* Update employee address_Id based on employeeName */
    public Integer updateEmpAddressIdByEmpName(@Param("employeeName") String employeeName, @Param("address_Id") Integer address_Id);

    /* Search Employee By Id*/
    public Employee getEmployeeById(@Param("Id") Integer Id);

    /* Update Employee By Id*/
    public Integer updateEmployeeById(Employee employee);
    /* Update Address By address_Id */
    public Integer updateAddressByAddressId(Address address);

    /* Delete Employees */
    public Integer deleteEmployees(String Ids[]);

    /* Search Employees By Id String Array*/
    public List<Employee> listEmployeesByIdsArray(String Ids[]);

    /* Delete Address By Address_Id Array*/
    public Integer deleteAddressByAddressIdArray(ArrayList arrayList);


    /* Ajax Load All Admins*/
    public List<Admin> listAllAdmins();

    /* List All admins by pagination*/
    public List<Admin> listAllAdminsByPagination(@Param("pageFirst") Integer pageFirst, @Param("pageSize")Integer pageSize, @Param("Sort") String Sort, @Param("Order") String Order);

    /* Add admins*/
    public Integer addAdmins(Admin admin);

    /* Get Admin By Id*/
    public Admin getAdminById(@Param("Id") Integer Id);

    /* Update Admin By Id*/
    public Integer updateAdminById(Admin admin);

    /* Delete Admins By Ids*/
    public Integer deleteAdmins(String Ids[]);

    /* List PC Resources By Pagination*/
    public List<PCResource> listAllPCResourcesByPagination(@Param("pageFirst") Integer pageFirst, @Param("pageSize") Integer pageSize, @Param("Sort") String Sort, @Param("Order") String Order, @Param("queryStr") String queryStr);

    /* List All pc Resources */
    public List<PCResource> listAllPCResources();

    /* Add PC Resource */
    public Integer addPCResource(PCResource pcResource);

    /* Get PC Record By ID*/
    public PCResource getPCRecordById(Integer Id);

    /* Update PC Record */
    public Integer updatePCRecord(PCResource pcResource);

    /* Delete PC Records */
    public Integer deletePCRecords(String Ids[]);

    /* Ajax Load All Depts*/
    public List<ITDepartment> listAllDeptsByPagination(@Param("pageFirst") Integer pageFirst, @Param("pageSize") Integer pageSize, @Param("Sort") String Sort, @Param("Order") String Order, @Param("queryStr") String queryStr);

    /* Load All Depts*/
    public List<ITDepartment> listAllDepts();

    /* Ajax Load All HR depts */
    public List<HRDepartment> listAllHRDeptsByPagination(@Param("pageFirst") Integer pageFirst, @Param("pageSize") Integer pageSize, @Param("Sort") String Sort, @Param("Order") String Order, @Param("queryStr") String queryStr);

    public List<HRDepartment> listAllHRDepts();
}
