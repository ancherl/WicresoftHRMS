<%--
  Created by IntelliJ IDEA.
  User: daixin
  Date: 2019-01-07
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

    <table id="pcResource">

    </table>

    <div id="tt_pcResource">
        <div>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" Plain="true" onclick="pc_add()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" Plain="true" onclick="pc_edit()">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" Plain="true" onclick="pc_delete()">删除</a>
        </div>
    </div>

    <%-- PCs add--%>
    <form id="pcs_add" style="margin:0; padding: 5px 20px 0 80px; color: #333" enctype="multipart/form-data">
        <table style="width: 340px">
            <tr>
                <td>品牌: </td>
                <td><input type="text" name="brand" class="textbox" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td>处理器:</td>
                <td><input type="text" name="processor" class="textbox" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td>内存:</td>
                <td><input type="text" name="memory" class="textbox" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td>序列码:</td>
                <td><input type="text" name="serialNumber" class="textbox" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td>图片:</td>
                <td><input type="file" name="file" id="image_input" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="button" value="Preview" onclick="imgPre()" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td></td>
                <td colspan="2">
                    <div id="imgPreview" style="margin-left: 30px"></div>
                </td>
            </tr>
        </table>
    </form>

    <%-- PCs Edit--%>
    <form id="pcs_edit" style="margin:0; padding: 5px 20px 0 80px; color: #333" enctype="multipart/form-data">
        <table style="width: 340px">
            <input type="hidden" name="pc_id" class="textbox">
            <tr>
                <td>品牌: </td>
                <td><input type="text" name="brand_edit" class="textbox" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td>处理器:</td>
                <td><input type="text" name="processor_edit" class="textbox" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td>内存:</td>
                <td><input type="text" name="memory_edit" class="textbox" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td>序列码:</td>
                <td><input type="text" name="serialNumber_edit" class="textbox" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td></td>
                <td colspan="2">
                    <div id="imgPreview_edit" style="margin-left: 30px"></div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="file" name="file" id="image_input_edit" style="margin-left: 30px"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="button" value="Preview" onclick="imgPre_edit()" style="margin-left: 30px"></td>
            </tr>

        </table>
    </form>

    <script type="application/javascript" src="/js/admin/pcResourceManage.js"></script>
