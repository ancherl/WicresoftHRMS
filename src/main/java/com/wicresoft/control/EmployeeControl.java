package com.wicresoft.control;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.alibaba.fastjson.JSONArray;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DateTimeDV;
import com.wicresoft.entity.Employee;
import com.wicresoft.serviceImpl.EmployeeServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Emp")
@Api(value = "/Emp", description = "Employee Control Rest API")
public class EmployeeControl {

    /* 在配置AOP时， 下面的 @Annotation 可能会产生错误
    *  默认情况下, SpringAOP采用JDK动态代理, 但是次代理只支持interface注入， 不支持类注入
    *  如果想要下面的类注入在配置AOP时生效, 必须启用 CGLib 动态代理:
    *  <tx:annotation-driven proxy-target-class="true"></tx:annotation-driven>
    *  */
    @Resource
    private EmployeeServiceImpl employeeServiceImpl;

    @RequestMapping(value = "/getEmpsByPagination", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ApiOperation(notes = "/getEmpsByPagination",httpMethod = "GET", value = "GetEmpsByPagination")
    @ResponseBody
    public String getEmpsByPagination(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer pageSize,
                                      @RequestParam(value = "sort", required = false) String Sort, @RequestParam(value = "order", required = false) String Order, @RequestParam(value = "employeeName", required = false) String employeeName){
        List<Employee> empList= employeeServiceImpl.getEmployeesByPagination((page-1)*pageSize,pageSize, Sort, Order, employeeName);

        /*获取所有员工的数量， 用于填充Pagination tool bar*/
        List<Employee> allEmps=employeeServiceImpl.getAllEmployees();


        String jsonString= "{" +
                "\"total\":"+ allEmps.size() +","+
                "\"rows\":" + JSONArray.toJSONString(empList) +
                "}";


        return jsonString;
    }

    /**
     *  Validatebox Test
     */
    @RequestMapping("/FormBox")
    @ApiOperation(notes = "/validateBox",httpMethod = "GET", value = "ValidateBox")
    @ResponseBody
    public Map<String, Object> ValidateBox(@ApiParam(name = "userName", value = "用户名",required = true) String userName, @ApiParam(name = "Email", value = "Email",required = true) String Email){
        System.out.println("Name:"+userName+", Email:"+Email);
        Map<String, Object> map=new HashMap<>();
        map.put("userName",userName);
        map.put("Email",Email);
        return map;
    }

    /*
    *  List All Employees
    * */
    @RequestMapping("/getAllEmps")
    @ResponseBody
    public List<Employee> getAllEmployees(){
        List<Employee> allEmployees= employeeServiceImpl.getAllEmployees();

        return allEmployees;
    }

    /**
     * Delete Employees based on Ids
     */
    @RequestMapping("/deleteEmps")
    @ResponseBody
    public Integer deleteEmployees(@RequestParam("ids") String idsString){
        Integer result= employeeServiceImpl.deleteEmployees(idsString.split(","));


        return result;
    }

    /* Add Employees */
    @RequestMapping("/addEmps")
    @ResponseBody
    public Integer addEmployee(Employee employee){
        Integer result= employeeServiceImpl.addEmployees(employee);

        return result;
    }

    /**
     *  Update Employee
     *  Bulk Update is not supported
     */
    @RequestMapping("/updateEmps")
    @ResponseBody
    public Integer updateEmployee(Employee employee){
        Integer result= employeeServiceImpl.updateEmployees(employee);

        return result;
    }

    /**
     * @author daixin
     * Upload images
     */
//    @RequestMapping("/ajaxUploadImages")
//    @ResponseBody
//    public String UploadImages(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
//        String fileName=file.getOriginalFilename();
//
//        String bathPath="/Users/daixin/JavaProject/WicreosoftHRMS/src/main/webapp/images/admin/pc_resource";
//
//        LocalDateTime currentTime=LocalDateTime.now();
//        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMDDHHmmss");
//
//        /* 重命名上传的文件 */
//        String savedFileName=dateTimeFormatter.format(currentTime)+"."+fileName.substring(fileName.lastIndexOf(".")+1);
//
//        String path=request.getSession().getServletContext().getRealPath("images/admin/pc_resource");
////        File filePath=new File(path);
////        /* 创建目录 */
////        if (!filePath.exists() && !filePath.isDirectory()){
////            System.out.println("目录不存在， 创建目录: "+ filePath);
////            filePath.mkdirs();
////        }
//        File filePath=new File(bathPath);
//        if (!filePath.exists() && !filePath.isDirectory()){
//            System.out.println("目录不存在， 创建目录: "+ filePath);
//            filePath.mkdirs();
//        }
//        /*在指定路径中创建文件*/
////        File targetFile=new File(path, savedFileName);
//        File targetFile=new File(bathPath, savedFileName);
//
//        try {
//            file.transferTo(targetFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return "pc_resource/"+savedFileName;
//    }

}
