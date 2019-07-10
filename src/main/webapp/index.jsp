<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Blog Index Page</title>
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.6.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.6.3/themes/icon.css">
    <script type="text/javascript" src="/js/jquery-easyui-1.6.3/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-easyui-1.6.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/jqueryForm/jquery.form.js"></script>
    <script type="text/javascript" src="/js/Index.js"></script>

</head>
<body>
<a href="/admin/loginAdmin">后台</a>

<%-- Upload Images--%>
<table>
    <tr>
        <td colspan="2">
            <form id="formPhoto" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td>选择文件: <input type="file" name="file" id="image_input"></td>
                        <td><input type="button" value="上传" id="uploadImg" onclick="uploadPhoto()"></td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <div id="imgDiv"></div>
        </td>
    </tr>
</table>

</body>
</html>
