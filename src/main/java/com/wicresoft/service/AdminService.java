package com.wicresoft.service;

import com.wicresoft.entity.*;

import java.util.ArrayList;
import java.util.List;

public interface AdminService {

    /* Check if Admin account is existed*/
    public Admin checkAdminAccount(String account);

    /* List All menu options*/
    public List<AdminMenuOption> getMenuOptions(Integer child_id);

    /* list All Employees By Pagination */
    public List<Employee> listAllEmployeesByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr);

    /* List All Employees */
    public List<Employee> listAllEmployees();

    /* Add new Employee*/
    public Integer addEmployee(Employee employee);

    /* Add new Address */
    public Integer addAddress(Address address);

    /* List All addresses*/
    public List<Address> listAllAddresses();

    /* Update employee address_Id based on employeeName */
    public Integer updateEmpAddressIdByEmpName(String employeeName, Integer address_Id);

    /* get Employee By Id*/
    public Employee getEmployeeById(Integer Id);

    /* Update Employee By Id*/
    public Integer updateEmployeeById(Employee employee);

    /* Update Address By address Id*/
    public Integer updateAddressByAddressId(Address address);

    /* Delete Employees */
    public Integer deleteEmployees(String Ids[]);

    /* list all employees based on Ids array*/
    public List<Employee> listEmployeesByIdsArray(String Ids[]);

    /* Delete Address By address_Id Array*/
    public Integer deleteAddressByAddressIdArray(ArrayList arrayList);

    /* Ajax Load All Admins*/
    public List<Admin> listAllAdmins();

    public List<Admin> listAllAdminsByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order);

    /* Admin admins*/
    public Integer addAdmins(Admin admin);

    /* Get Admin By Id*/
    public Admin getAdminById(Integer Id);

    /* Update Admin By Id*/
    public Integer updateAdminById(Admin admin);

    /* Delete Admins By Ids array */
    public Integer deleteAdmins(String Ids[]);

    /* List All PC Resources By Pagination */
    public List<PCResource> listAllPCResourcesByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr);

    /* List All PC Resources */
    public List<PCResource> listAllPCResources();

    /* Add PC Resource */
    public Integer addPCResource(PCResource pcResource);

    /* Get PC Record By ID*/
    public PCResource getPCRecordById(Integer Id);

    /* Update PC Record */
    public Integer updatePCRecord(PCResource pcResource);

    /* Delete PC Records */
    public Integer deletePCRecords(String Ids[]);

    /* Ajax Load Depts By Pagination */
    public List<ITDepartment> listAllDeptsByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr);

    /* Load All Depts*/
    public List<ITDepartment> listAllDepts();

    public List<HRDepartment> listAllHRDeptsByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr);

    public List<HRDepartment> listAllHRDepts();
}
