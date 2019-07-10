<%--
  Created by IntelliJ IDEA.
  User: daixin
  Date: 2019-02-02
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table id="itDepartment">

</table>
<div id="tt_itDepartment">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" Plain="true" href="#" onclick="emp_add()">添加</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" Plain="true" href="#" onclick="emp_edit()">修改</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" Plain="true" href="#" onclick="emp_delete()">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-save" Plain="true" href="#" style="display: inline-block" id="save" onclick="save()">保存</a>
        <a class="easyui-linkbutton" iconCls="icon-redo" Plain="true" href="#" style="display: inline-block;" id="redo" onclick="redo()">取消编辑</a>
    </div>
    <div id="search_employee">
        部门姓名: <input type="text" class="textbox" name="empName">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="Search()">查询</a>
    </div>
</div>

<script type="application/javascript" src="/js/admin/itDepartmentManage.js"></script>