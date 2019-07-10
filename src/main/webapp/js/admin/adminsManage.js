$(document).ready(function () {

    $.extend($.fn.validatebox.defaults.rules, {
        minLength: {
            validator: function(value, param){
                return value.length >= param[0];
            },
            message: 'Please enter at least {0} characters.'
        }
    })

    $('#admins').datagrid({
        title: '管理员列表',
        url: '/admin/ajaxLoadAdmins',
        iconCls: 'icon-admin',
        columns: [[
            {
                field: 'id',
                title: 'id',
                checkbox: true
            },
            {
                field: 'account',
                title: '账户名',
                sortable: true
            },
            {
                field: 'password',
                title: '密码',
            },
            {
                field: 'created',
                title: '创建时间',
                formatter: function (value, row, index) {
                    return new Date(value*1000).toLocaleString()
                }
            }
        ]],
        pagination: true,
        rownumbers: true,
        striped: true,
        pageSize: 2,
        pageList: [2,5,10,20],
        fitColumns: true,
        toolbar: '#tt_admins'
    })

    /* Add admin */
    $('#admins_add').dialog({
        title: '新增管理员',
        width: 315,
        iconCls: 'icon-admin',
        buttons: [
            {
                text: 'Save',
                iconCls: 'icon-ok',
                handler: function(){
                    if ($('#admins_add').form('validate')){
                        $.ajax({
                            type: 'post',
                            url: '/admin/addAdmins',
                            dataType: 'json',
                            data: {
                                account: $('input[name="account"]').val(),
                                password: $('input[name="password"]').val()
                            },
                            success: function (data, res, state) {
                                if (data>0){
                                    $.messager.show({
                                        title: 'Admin Add Info',
                                        msg: 'A new admin added successfully'
                                    })
                                } else {
                                    $.messager.alert('Alert Info', "Failed to add new admin, please try again!!", 'error');
                                }

                                $('#admins_add').form('reset').dialog('close');
                                $('#admins').datagrid('reload')
                            },
                            error: function () {
                                $.messager.alert("Alert Info","Server Request Failed!",'error')
                            }
                        })
                    }
                }
            },
            {
                text: 'Cancel',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#admins_add').form('reset').dialog('close');
                }
            }
        ],
        modal: true,
        closed: true
    })

    /* Edit admin*/
    $('#admins_edit').dialog({
        title: '编辑管理员',
        width: 315,
        iconCls: 'icon-admin',
        buttons: [
            {
                text: 'Save',
                iconCls: 'icon-ok',
                handler: function(){
                    $.ajax({
                        type: 'post',
                        url: '/admin/updateAdminById',
                        dataType: 'json',
                        data: {
                            id: $('input[name="admin_id"]').val(),
                            account: $('input[name="account_edit"]').val(),
                            password: $('input[name="password_edit"]').val()
                        },
                        success: function (data, res, state) {
                            if (data>0){
                                $.messager.show({
                                    title: 'Admin Update Info',
                                    msg: 'An admin updated successfully'
                                })
                            } else {
                                $.messager.alert('Alert Info', "Admin Info Update Failed, Please Try Again!", 'error');
                            }

                            $('#admins_edit').form('reset').dialog('close');
                            $('#admins').datagrid('reload')
                        },
                        error: function () {
                            $.messager.alert("Alert Info","Server Request Failed!",'error')
                        }
                    })
                }
            },
            {
                text: 'Cancel',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#admins_edit').form('reset').dialog('close');
                }
            }
        ],
        modal: true,
        closed: true
    })


    $('input[name="account"]').validatebox({
        required: true
    })

    $('input[name="password"]').validatebox({
        required: true,
        validType: 'minLength[6]'
    })

    $('input[name="password_edit"]').validatebox({
        required: true,
        validType: 'minLength[6]'
    })
})

function admins_add(){
    $('#admins_add').dialog('open');
}
function admins_edit() {
    var rows=$('#admins').datagrid('getSelections');
    if (rows.length<1){
        $.messager.alert('Alert Info', 'Please select the record you want to edit firstly!','warning')
    } else if(rows.length===1){
        $.ajax({
            type: 'get',
            url: '/admin/getAdminById',
            dataType: 'json',
            data: {
                Id: rows[0].id
            },
            success: function (data, res, state) {
                if (data){
                    $('#admins_edit').form('load',{
                        admin_id: data.id,
                        account_edit: data.account
                    }).dialog('open')
                } else {
                    $.messager.alert("Retrieve Failure","Retrieve Info Failed!",'error')
                }
            },
            error: function () {
                $.messager.alert("Alert Info","Server Request Failed!",'error')
            }
        })
    }else {
        $.messager.alert('Alert Info', 'You could only select one record to edit at most once time','warning')
    }
}

function admins_delete() {
    var rows=$('#admins').datagrid('getSelections');
    if (rows.length>0){
        $.messager.confirm("Confirm Info", "Do you want to delete the selected row(s)?", function(r){
            if (r){
                var ids=[];
                for (var i=0; i<rows.length; i++) {
                    ids.push(rows[i].id)
                }
                if (ids.indexOf(1)===-1){
                    $.ajax({
                        type: 'post',
                        url: '/admin/deleteAdmins',
                        dataType: 'json',
                        data: {
                            Ids: ids.join(',')
                        },
                        success: function (data, res, state) {
                            if (data>0){
                                $.messager.show({
                                    title: 'Admin Delete Info',
                                    msg: 'An admin deleted successfully'
                                })
                            } else {
                                $.messager.alert("Alert Info","Admin Info Delete Failed!",'error')
                            }
                            $('#admins').datagrid('reload')
                        },
                        error: function () {
                            $.messager.alert("Alert Info","Server Request Failed!",'error')
                        }
                    })
                } else {
                    $.messager.alert("Alert Info",'The "admin" account is super administrator, which could not be deleted!','error')
                }

            } else {
                $('#admins').datagrid('unselectAll')
            }
        })
    } else {
        $.messager.alert("Alert Info","Please select a row or rows you want to delete",'info')
    }
}

