<%--
  Created by IntelliJ IDEA.
  User: daixin
  Date: 2018-12-01
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Index Page</title >
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.6.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.6.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/css/admin/adminIndex.css">
    <style>
        .textbox {
            height: 20px;
            margin: 0;
            padding: 0 2px;
            box-sizing: content-box;
        }
    </style>


</head>
<body class="easyui-layout">
    <div data-options="region:'north', title:'North Title', noheader: true" style="height: 100px;background-image: url('/images/Top_bg.jpg');background-repeat: no-repeat">
        <div class="logo">WicresoftHRMS 后台管理</div>
        <div class="logout">Welcome <%= session.getAttribute("account")%> | <a href="/admin/logoutAdmin">Log out</a></div>
    </div>
    <div data-options="region:'south', title:'South', noheader: true" style="text-align: center;height: 40px; padding-top: 8px; padding-bottom: 8px ">
        &copy;2018-2019 Wicresoft HR Management 版权所有. Powered By SSM.
    </div>
    <div data-options="region:'west', title:'Menu Options', split:true, iconCls:'icon-menu'" style="width: 180px">
        <ul id="nav">

        </ul>
    </div>
    <div data-options="region:'center', split:true">
        <div id="tabs">
            <div title="起始页" style="padding:  0 10px;display: block" data-options="iconCls:'icon-home'">
                Welcome Wicresoft HR Management System
            </div>
        </div>
    </div>



    <script type="text/javascript" src="/js/jquery-easyui-1.6.3/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-easyui-1.6.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/jqueryForm/jquery.form.js"></script>
    <script type="text/javascript" src="/js/admin/adminIndex.js"></script>


</body>
</html>
