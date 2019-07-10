<%--
  Created by IntelliJ IDEA.
  User: daixin
  Date: 2018-12-10
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <table id="employee">

    </table>
    <div id="tt_employee">
        <div>
            <a class="easyui-linkbutton" iconCls="icon-add" Plain="true" href="#" onclick="emp_add()">添加</a>
            <a class="easyui-linkbutton" iconCls="icon-edit" Plain="true" href="#" onclick="emp_edit()">修改</a>
            <a class="easyui-linkbutton" iconCls="icon-remove" Plain="true" href="#" onclick="emp_delete()">删除</a>
            <a class="easyui-linkbutton" iconCls="icon-save" Plain="true" href="#" style="display: inline-block" id="save" onclick="save()">保存</a>
            <a class="easyui-linkbutton" iconCls="icon-redo" Plain="true" href="#" style="display: inline-block;" id="redo" onclick="redo()">取消编辑</a>
        </div>
        <div id="search_employee">
            员工姓名: <input type="text" class="textbox" name="empName">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="Search()">查询</a>
        </div>
    </div>
    <form id="employee_add" style="margin:0; padding: 5px 30px 0 60px; color: #333">
        <table style="width: 260px">
            <tr>
                <td>姓名: </td>
                <td><input type="text" name="employeeName" class="textbox"></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td><input type="text" name="department" class="textbox"></td>
            </tr>
            <tr>
                <td>邮件:</td>
                <td><input type="text" name="email" class="textbox"></td>
            </tr>
            <tr>
                <td>省份:</td>
                <td><input type="text" name="province" class="textbox"></td>
            </tr>
            <tr>
                <td>城市:</td>
                <td><input type="text" name="cityName" class="textbox"></td>
            </tr>
        </table>
    </form>

    <form id="employee_edit" style="margin:0; padding: 5px 30px 0 60px; color: #333">
        <table style="width: 260px">
            <input type="hidden" name="employee_id" class="textbox">
            <tr>
                <td>姓名: </td>
                <td><input type="text" name="employeeName_edit" class="textbox" disabled="true" style="background-color: #9E9E9E"></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td><input type="text" name="department_edit" class="textbox"></td>
            </tr>
            <tr>
                <td>邮件:</td>
                <td><input type="text" name="email_edit" class="textbox"></td>
            </tr>
            <tr>
                <td>省份:</td>
                <td><input type="text" name="province_edit" class="textbox"></td>
            </tr>
            <tr>
                <td>城市:</td>
                <td><input type="text" name="cityName_edit" class="textbox"></td>
            </tr>
        </table>
    </form>

    <script type="application/javascript" src="/js/admin/employeeManage.js"></script>


