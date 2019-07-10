<%--
  Created by IntelliJ IDEA.
  User: daixin
  Date: 2018-12-10
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <table id="admins">

    </table>
    <div id="tt_admins">
        <div>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" Plain="true" onclick="admins_add()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" Plain="true" onclick="admins_edit()">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" Plain="true" onclick="admins_delete()">删除</a>
        </div>
    </div>

    <form id="admins_add" style="margin:0; padding: 5px 50px 0 40px; color: #333">
        <table style="width: 260px">
            <tr>
                <td>账户名: </td>
                <td><input type="text" name="account" class="textbox"></td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
                <td><input type="text" name="password" class="textbox"></td>
            </tr>
        </table>
    </form>

    <form id="admins_edit" style="margin:0; padding: 5px 50px 0 40px; color: #333">
        <table style="width: 260px">
            <input type="hidden" name="admin_id" class="textbox">
            <tr>
                <td>账户名: </td>
                <td><input type="text" name="account_edit" class="textbox" disabled="true" style="background-color: #9E9E9E"></td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
                <td><input type="text" name="password_edit" class="textbox" placeholder="请输入新密码"></td>
            </tr>
        </table>
    </form>

    <script type="application/javascript" src="/js/admin/adminsManage.js"></script>


