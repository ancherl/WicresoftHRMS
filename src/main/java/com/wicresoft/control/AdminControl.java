package com.wicresoft.control;

import com.alibaba.fastjson.JSONArray;
import com.wicresoft.entity.*;
import com.wicresoft.service.AdminService;
import com.wicresoft.serviceImpl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControl {
    @Resource
    private AdminServiceImpl adminServiceImpl;

    /**
     * Login Admin
     * @return
     */
    @RequestMapping("/loginAdmin")
    public String loginAdmin(){
        return "admin/adminLogin";
    }

    /**
     * @author daixin
     * Admin Login Validate
     */
    @RequestMapping("/checkAdmin")
    @ResponseBody
    public String checkAdminAccount(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession httpSession){
        Admin admin=adminServiceImpl.checkAdminAccount(account);
        if (admin==null){
            return "-1";
        }else {
            if(DigestUtils.md5DigestAsHex(password.getBytes()).equals(admin.getPassword())){
//                httpSession.setMaxInactiveInterval(1800);
                httpSession.setAttribute("account",admin.getAccount());
                return "1";
            }
            else {
                return "0";
            }
        }
    }

    /**
     * @author daixin
     * 登录成功后跳转到 adminIndex 页面
     */
    @RequestMapping("/adminIndex")
    public String adminIndex(HttpSession httpSession){
        if (httpSession.getAttribute("account")!=null){
            return "admin/adminIndex";
        }else {
            return "admin/adminLogin";
        }

    }

    /**
     * Log Out
     * @author daixin
     * 退出登录
     */
    @RequestMapping("/logoutAdmin")
    public String logoutAdmin(HttpSession httpSession){
        httpSession.removeAttribute("account");
        if (httpSession.getAttribute("account")==null){
            return "admin/adminLogin";
        }else {
            return "admin/adminIndex";
        }
    }

    /* Admin Left Side Menu Options Load*/
    @RequestMapping("/menuOptions")
    @ResponseBody
    public List getAllMenuOptions(@RequestParam(value = "id", required = false) Integer id){
        if (id!=null){
            return adminServiceImpl.getMenuOptions(id);
        }else {
            return adminServiceImpl.getMenuOptions(0);
        }
    }

    /**
     * @author daixin
     * listEmps
     */
    @RequestMapping("/listEmps")
    public String listEmps(HttpSession httpSession){
        if (httpSession.getAttribute("account")==null){
            return "admin/adminLogin";
        }else {
            return "admin/employeeManage";
        }
    }

    /**
     * @author daixin
     * listEmps
     */
    @RequestMapping("/ajaxLoadEmps")
    @ResponseBody
    public String listAllEmps(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value="rows", required = false) Integer pageSize
                                                     ,  @RequestParam(value="sort", required = false) String Sort, @RequestParam(value = "order",required = false) String Order, @RequestParam(value = "employeeName", required = false) String employeeName, HttpSession httpSession){

            List<Employee> employeeList=adminServiceImpl.listAllEmployeesByPagination((page-1)*pageSize,pageSize,Sort,Order,employeeName);
            /* List All Emps */
            List<Employee> allEmps=adminServiceImpl.listAllEmployees();

            String jsonString="{"+
                       "\"total\":"+allEmps.size()+","+
                       "\"rows\":" + JSONArray.toJSONString(employeeList) +
                       "}";



            return jsonString;


    }

    /**
     * @author daixin
     * listManagers
     */
    @RequestMapping("/listManagers")
    public String listAllManagers(HttpSession httpSession){
        if (httpSession.getAttribute("account")==null){
            return "admin/adminLogin";
        }else {
            return "admin/teamLeaderManage";
        }

    }

    /**
     * @author daixin
     * Add new employee
     */
    @RequestMapping("/addEmployees")
    @ResponseBody
    public Integer addEmployees(Employee employee, Address address){

        /* Insert row into Employee table*/
        Integer result=adminServiceImpl.addEmployee(employee);

        /* Insert row into Address table */
        if (address.getProvince()!=null || address.getCityName()!=null){
            adminServiceImpl.addAddress(address);
            List<Address> addressList=adminServiceImpl.listAllAddresses();
//            addressList.get(addressList.size()-1).getAddress_Id()
            adminServiceImpl.updateEmpAddressIdByEmpName(employee.getEmployeeName(), addressList.get(addressList.size()-1).getAddress_Id());

        }

        if (result!=1){
            return 0;
        }else {
            return 1;
        }

    }

    /**
     * @author daixin
     * @ Get employee By Id
     */
    @RequestMapping("/getEmployeeById")
    @ResponseBody
    public Employee getEmployeeById(@RequestParam(value = "Id", required = false) Integer Id){
        Employee employee=adminServiceImpl.getEmployeeById(Id);

        return employee;
    }

    /**
     * @author daixin
     * @ Update Employee By Id
     */
    @RequestMapping("/updateEmployeeById")
    @ResponseBody
    public Integer updateEmployeeById(Employee employee, Address address){

        Integer result=adminServiceImpl.updateEmployeeById(employee);

        /* Retrieve corresponding address_Id based on Employee Id*/
        Integer address_Id=adminServiceImpl.getEmployeeById(employee.getId()).getAddress().getAddress_Id();
        address.setAddress_Id(address_Id);
        Integer result2=adminServiceImpl.updateAddressByAddressId(address);

        if (result!=1 || result2!=1){
            return 0;
        }else {
            return 1;
        }

    }

    /**
     * @author daixin
     * @  Delete Employees
     */
    @RequestMapping("/deleteEmployees")
    @ResponseBody
    public Integer deleteEmployees(@RequestParam("Ids") String Ids){

        ArrayList addressIdsArray=new ArrayList();
        List<Employee> employeeList=adminServiceImpl.listEmployeesByIdsArray(Ids.split(","));
        for (int i=0; i<employeeList.size(); i++){
            addressIdsArray.add(employeeList.get(i).getAddress().getAddress_Id());
        }
        Integer result2=adminServiceImpl.deleteAddressByAddressIdArray(addressIdsArray);


        Integer result=adminServiceImpl.deleteEmployees(Ids.split(","));

        if (result2<1 || result <1){
            return 0;
        }else {
            return 1;
        }

    }

    /**
     * @author daixin
     * 后台管理员管理
     */
    @RequestMapping("/listAdmins")
    public String listAllAdmins(HttpSession httpSession){
        if (httpSession.getAttribute("account")==null){
            return "admin/adminLogin";
        }else {
            return "admin/adminsManage";
        }
    }

    /**
     * @author daixin
     * Ajax remote load --> admins
     */
    @RequestMapping("/ajaxLoadAdmins")
    @ResponseBody
    public String listAllAdmins(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer pageSize, @RequestParam(value = "sort", required = false) String Sort, @RequestParam(value = "order", required = false) String Order){
        List<Admin> adminList=adminServiceImpl.listAllAdminsByPagination((page-1)*pageSize, pageSize, Sort, Order);

        /* List All admins */
        List<Admin> allAdmins=adminServiceImpl.listAllAdmins();

        String jsonString= "{"+
                "\"total\":"+allAdmins.size()+","+
                "\"rows\":" + JSONArray.toJSONString(adminList) +
                "}";

        return jsonString;
    }

    /**
     * @author daixin
     * Add Admins
     */
    @RequestMapping("/addAdmins")
    @ResponseBody
    public Integer addAdmins(Admin admin){

        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));

        admin.setCreated(new Date().getTime()/1000);

        Integer result=adminServiceImpl.addAdmins(admin);

        if (result>0){
            return 1;
        }else {
            return 0;
        }

    }

    /**
     * @author daixin
     * Get Admin By Id
     */
    @RequestMapping("/getAdminById")
    @ResponseBody
    public Admin getAdminById(Integer Id){
        Admin admin = adminServiceImpl.getAdminById(Id);


        return admin;
    }

    /**
     * @author daixin
     * Edit Admins
     */
    @RequestMapping("/updateAdminById")
    @ResponseBody
    public Integer updateAdminById(Admin admin){

        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));

        Integer result=adminServiceImpl.updateAdminById(admin);

        if (result>0){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * @author daixin
     * Delete Admins By Ids array
     */
    @RequestMapping("/deleteAdmins")
    @ResponseBody
    public Integer deleteAdmins(@RequestParam("Ids") String Ids){
        Integer result=adminServiceImpl.deleteAdmins(Ids.split(","));

        if (result>0){
            return 1;
        }else {
            return 0;
        }
    }


    /**
     * @author daixin
     * listPCResource
     */
    @RequestMapping("/listPCResource")
    public String listAllPCResources(HttpSession httpSession){
        if (httpSession.getAttribute("account")==null){
            return "admin/adminLogin";
        }else {
            return "admin/pcResourceManage";
        }
    }


    /**
     * @author daixin
     *
     * ajax load PC resources
     */
    @RequestMapping("/ajaxLoadPCResources")
    @ResponseBody
    public String ajaxLoadPCResources(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value="rows", required = false) Integer pageSize
            , @RequestParam(value="sort", required = false) String Sort, @RequestParam(value = "order",required = false) String Order, @RequestParam(value = "brand", required = false) String brand, HttpSession httpSession){

        List<PCResource> pcResourcesList = adminServiceImpl.listAllPCResourcesByPagination((page-1)*pageSize, pageSize, Sort, Order, brand);


        /* List All PC Resources */
        List<PCResource> allPCResources = adminServiceImpl.listAllPCResources();


        String jsonString = "{"+
                "\"total\":"+ allPCResources.size() +","+
                "\"rows\":" + JSONArray.toJSONString(pcResourcesList) +
                "}";

        return jsonString;

    }

    /**
     * @author daixin
     * preview upload image in PC Resource Part
     */
    @RequestMapping("/previewImage")
    @ResponseBody
    public String preImage(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        String fileName=file.getOriginalFilename();

        String bathPath="/Users/daixin/JavaProject/WicreosoftHRMS/src/main/webapp/images/admin/pc_resource";

        LocalDateTime currentTime=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMDDHHmmss");

        /* 重命名上传的文件 */
        String savedFileName=dateTimeFormatter.format(currentTime)+"."+fileName.substring(fileName.lastIndexOf(".")+1);

        File filePath=new File(bathPath);
        if (!filePath.exists() && !filePath.isDirectory()){
            System.out.println("目录不存在， 创建目录: "+ filePath);
            filePath.mkdirs();
        }

        /*在指定路径中创建文件*/
//        File targetFile=new File(path, savedFileName);
        File targetFile=new File(bathPath, savedFileName);

        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "admin/pc_resource/" + savedFileName;

    }

    /**
     * @author daixin
     *
     * Delete Cached Preview Image file
     */
    @RequestMapping("/deleteCachedPreImage")
    @ResponseBody
    public Integer deleteCachedPreImage(@RequestParam(value = "filePath", required = false) String filePath){
        File deletePath=new File(filePath);

        if (!deletePath.exists() && !deletePath.isDirectory()){
            return 0;
        }else {
            deletePath.delete();

            return 1;
        }
    }

    /**
     * @author daixin
     * Add new PC resource
     */
    @RequestMapping("/addNewPCResource")
    @ResponseBody
    public Integer addNewPCResource(@RequestParam(value = "file", required = false) MultipartFile file, PCResource pcResource){
        String bathPath="/Users/daixin/JavaProject/WicreosoftHRMS/src/main/webapp/images/admin/pc_resource";

        if (file!=null){
            /* 重命名上传的文件 */
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMDDHHmmss");
            String savedFileName=dateTimeFormatter.format(LocalDateTime.now())+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);

            if (!new File(bathPath).exists() && !new File(bathPath).isDirectory()){
                System.out.println("目录不存在， 创建目录: "+ new File(bathPath));
                new File(bathPath).mkdirs();
            }

            /*在指定路径中创建文件*/
//        File targetFile=new File(path, savedFileName);
            File targetFile=new File(bathPath, savedFileName);

            try {
                file.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            pcResource.setImageUrl("admin/pc_resource/"+savedFileName);
        }


        Integer result=adminServiceImpl.addPCResource(pcResource);

        if (result>0){
            return 1;
        }else {
            return 0;
        }

    }

    @RequestMapping("/addNewPCResource1")
    @ResponseBody
    public Integer addNewPCResource1(@RequestParam(value = "temporaryFile", required = false) String tempFile, PCResource pcResource){

        /* tempFile value 为完整的file path, 我们需要获得相对的file path*/
        String relativePath=tempFile.substring(tempFile.lastIndexOf("images")+6);

        pcResource.setImageUrl(relativePath);

        Integer result = adminServiceImpl.addPCResource(pcResource);

        if (result>0){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * @author daixin
     * Get PC record by Id
     */
    @RequestMapping("/getPCRecordById")
    @ResponseBody
    public PCResource getPCRecordById(@RequestParam("Id") Integer Id){

        return adminServiceImpl.getPCRecordById(Id);
    }


    /**
     * @author daixin
     * Update PC record
     */
    @RequestMapping("/updatePCRecord")
    @ResponseBody
    public Integer updatePCRecord(@RequestParam(value = "temporaryFile", required = false) String tempFile, PCResource pcResource){
        /* tempFile value 为完整的file path, 我们需要获得相对的file path*/
        String relativePath=tempFile.substring(tempFile.lastIndexOf("images")+6);

        pcResource.setImageUrl(relativePath);

        Integer result = adminServiceImpl.updatePCRecord(pcResource);

        if (result>0){
            return 1;
        }else {
            return 0;
        }


    }

    @RequestMapping("/updatePCRecord1")
    @ResponseBody
    public Integer updatePCRecord1(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("brand_edit") String brand,
                                   @RequestParam("processor_edit") String processor, @RequestParam("memory_edit") String memory,
                                   @RequestParam("serialNumber_edit") String serialNumber, @RequestParam("pc_id") Integer id){

        String bathPath="/Users/daixin/JavaProject/WicreosoftHRMS/src/main/webapp/images/admin/pc_resource";

        PCResource pcResource=new PCResource();

        if (file!=null){
            /* 重命名上传的文件 */
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMDDHHmmss");
            String savedFileName=dateTimeFormatter.format(LocalDateTime.now())+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);

            if (!new File(bathPath).exists() && !new File(bathPath).isDirectory()){
                new File(bathPath).mkdirs();
            }

            /*在指定路径中创建文件*/
            try {
                file.transferTo(new File(bathPath, savedFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            pcResource.setImageUrl("admin/pc_resource/"+savedFileName);
        }

        pcResource.setBrand(brand);
        pcResource.setProcessor(processor);
        pcResource.setMemory(memory);
        pcResource.setSerialNumber(serialNumber);
        pcResource.setId(id);

       Integer result= adminServiceImpl.updatePCRecord(pcResource);

       if (result>0){
           return 1;
       }else {
           return 0;
       }
    }

    /**
     * @author daixin
     * Delete PC Records
     */
    @RequestMapping("/deletePCRecords")
    @ResponseBody
    public Integer deletePCRecords(@RequestParam("Ids") String Ids){

        Integer result = adminServiceImpl.deletePCRecords(Ids.split(","));

        if (result>0){
            return 1;
        }else {
            return 0;
        }

    }


    /**
     * @author daixin
     * IT部门管理
     *
     */
    @RequestMapping("/listITDepts")
    public String listAllDepts(HttpSession httpSession){
        if (httpSession.getAttribute("account")==null){
            return "admin/adminLogin";
        }else {
            return "admin/itDepartmentManage";
        }
    }

    /**
     * @author daixin
     * Ajax 加载 IT Departments
     */
    @RequestMapping("/ajaxLoadITDepts")
    @ResponseBody
    public String listAllITDepts(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value="rows", required = false) Integer pageSize
            ,  @RequestParam(value="sort", required = false) String Sort, @RequestParam(value = "order",required = false) String Order, @RequestParam(value = "departmentName", required = false) String departmentName){

        List<ITDepartment> deptList=adminServiceImpl.listAllDeptsByPagination(pageSize*(page-1), pageSize, Sort, Order, departmentName);

        /* List All Depts */
        List<ITDepartment> allDepts=adminServiceImpl.listAllDepts();

        String jsonString="{"+
                "\"total\":"+allDepts.size()+","+
                "\"rows\":" + JSONArray.toJSONString(deptList) +
                "}";

        return jsonString;


    }

    /**
     * HR Depart management
     */
    @RequestMapping("/listHRDepts")
    public String listHRDepts(HttpSession httpSession){

        if (httpSession.getAttribute("account")==null){
            return "admin/adminLogin";
        }else {
            return  "admin/hrDepartmentManage";
        }
    }

    /**
     * Ajax Load HR Departments
     */
    @RequestMapping("/ajaxLoadHRDepts")
    @ResponseBody
    public String listAllHRDepts(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value="rows", required = false) Integer pageSize
            ,  @RequestParam(value="sort", required = false) String Sort, @RequestParam(value = "order",required = false) String Order, @RequestParam(value = "departmentName", required = false) String departmentName){

        /* Search By Pagination */
        List<HRDepartment> hrDeptList = adminServiceImpl.listAllHRDeptsByPagination(pageSize*(page-1), pageSize, Sort, Order, departmentName);

        /* List All HR Depts */
        List<HRDepartment> allHRDepts = adminServiceImpl.listAllHRDepts();

        String jsonString="{"+
                "\"total\":"+allHRDepts.size()+","+
                "\"rows\":" + JSONArray.toJSONString(hrDeptList) +
                "}";


        return jsonString;

    }






}
