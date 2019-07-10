<%--
  Created by IntelliJ IDEA.
  User: daixin
  Date: 2018-11-25
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Admin Login Page</title>
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.6.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.6.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/css/admin/adminLogin.css">

</head>
<body>
    <div id="login">
        <p>管理员账户: <input type="text" id="account" class="textbox"></p>
        <p>管理员密码: <input type="password" id="password" class="textbox"></p>
    </div>
    <div id="btn">
        <a href="#" class="easyui-linkbutton">登录</a>
    </div>



    <script type="text/javascript" src="/js/jquery-easyui-1.6.3/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-easyui-1.6.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/admin/adminLogin.js"></script>

</body>
</html>
