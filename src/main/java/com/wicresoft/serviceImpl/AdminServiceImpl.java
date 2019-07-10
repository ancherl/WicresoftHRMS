package com.wicresoft.serviceImpl;

import com.wicresoft.dao.AdminDao;
import com.wicresoft.entity.*;
import com.wicresoft.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public Admin checkAdminAccount(String account) {
        return adminDao.checkAdminAccount(account);
    }

    @Override
    public List<AdminMenuOption> getMenuOptions(Integer child_id) {
        return adminDao.getMenuOptions(child_id);
    }

    @Override
    public List<Employee> listAllEmployeesByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr) {
        return adminDao.listAllEmployeesByPagination(pageFirst, pageSize, Sort, Order, queryStr);
    }

    @Override
    public List<Employee> listAllEmployees() {
        return adminDao.listAllEmployees();
    }

    @Override
    public Integer addEmployee(Employee employee) {
        return adminDao.addEmployee(employee);
    }

    @Override
    public Integer addAddress(Address address) {
        return  adminDao.addAddress(address);
    }

    @Override
    public List<Address> listAllAddresses() {
        return adminDao.listAllAddresses();
    }

    @Override
    public Integer updateEmpAddressIdByEmpName(String employeeName, Integer address_Id) {
        return adminDao.updateEmpAddressIdByEmpName(employeeName, address_Id);
    }

    @Override
    public Employee getEmployeeById(Integer Id) {
        return adminDao.getEmployeeById(Id);
    }

    @Override
    public Integer updateEmployeeById(Employee employee) {
        return adminDao.updateEmployeeById(employee);
    }

    @Override
    public Integer updateAddressByAddressId(Address address) {
        return adminDao.updateAddressByAddressId(address);
    }

    @Override
    public Integer deleteEmployees(String[] Ids) {
        return adminDao.deleteEmployees(Ids);
    }

    @Override
    public List<Employee> listEmployeesByIdsArray(String[] Ids) {
        return adminDao.listEmployeesByIdsArray(Ids);
    }

    @Override
    public Integer deleteAddressByAddressIdArray(ArrayList arrayList) {
        return adminDao.deleteAddressByAddressIdArray(arrayList);
    }

    @Override
    public List<Admin> listAllAdmins() {
        return adminDao.listAllAdmins();
    }

    @Override
    public List<Admin> listAllAdminsByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order) {
        return adminDao.listAllAdminsByPagination(pageFirst, pageSize, Sort, Order);
    }

    @Override
    public Integer addAdmins(Admin admin) {
        return adminDao.addAdmins(admin);
    }

    @Override
    public Admin getAdminById(Integer Id) {
        return adminDao.getAdminById(Id);
    }

    @Override
    public Integer updateAdminById(Admin admin) {
        return adminDao.updateAdminById(admin);
    }

    @Override
    public Integer deleteAdmins(String[] Ids) {
        return adminDao.deleteAdmins(Ids);
    }

    @Override
    public List<PCResource> listAllPCResourcesByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr) {
        return adminDao.listAllPCResourcesByPagination(pageFirst, pageSize, Sort, Order, queryStr);
    }

    @Override
    public List<PCResource> listAllPCResources() {
        return adminDao.listAllPCResources();
    }

    @Override
    public Integer addPCResource(PCResource pcResource) {
        return adminDao.addPCResource(pcResource);
    }

    @Override
    public PCResource getPCRecordById(Integer Id) {
        return adminDao.getPCRecordById(Id);
    }

    @Override
    public Integer updatePCRecord(PCResource pcResource) {
        return adminDao.updatePCRecord(pcResource);
    }

    @Override
    public Integer deletePCRecords(String[] Ids) {
        return adminDao.deletePCRecords(Ids);
    }

    @Override
    public List<ITDepartment> listAllDeptsByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr) {
        return adminDao.listAllDeptsByPagination(pageFirst, pageSize, Sort, Order, queryStr);
    }

    @Override
    public List<ITDepartment> listAllDepts() {
        return adminDao.listAllDepts();
    }

    @Override
    public List<HRDepartment> listAllHRDeptsByPagination(Integer pageFirst, Integer pageSize, String Sort, String Order, String queryStr) {
        return adminDao.listAllHRDeptsByPagination(pageFirst, pageSize, Sort, Order, queryStr);
    }

    @Override
    public List<HRDepartment> listAllHRDepts() {
        return adminDao.listAllHRDepts();
    }
}
