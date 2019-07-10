$(document).ready(function(){
    $('#employee').datagrid({
        title: '员工列表',
        url: '/admin/ajaxLoadEmps',
        iconCls: 'icon-employee',
        columns: [
            [
                {
                    field: 'id',
                    title: 'ID',
                    checkbox: true
                },
                {
                    field: 'employeeName',
                    title: '员工姓名',
                    sortable: true,
                    editor: {
                        type: "validatebox",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'department',
                    title: '部门',
                    sortable: true,
                    editor: {
                        type: "validatebox",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'email',
                    title: '邮件',
                    sortable: true,
                    editor: {
                        type: "validatebox",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'province', /* 这边设置的field 是没有用的， 通过formatter进行值的显示*/
                    title: '省份',
                    sortable: true,
                    formatter: function(value, row, index){
                        if (row.address!=null){
                            return row.address.province;
                        }else {
                            return null
                        }
                    }
                },
                {
                    field: 'cityName',   /* 这边设置的field 是没有用的， 通过formatter进行值的显示*/
                    title: '城市',
                    sortable: true,
                    formatter: function (value, row, index) {
                        if (row.address!=null){
                            return row.address.cityName;
                        } else {
                            return null
                        }
                    }
                }
            ]
        ],
        pagination: true,
        rownumbers: true,
        striped: true,
        pageSize: 2,
        pageList: [2,5,10,20],
        fitColumns: true,
        toolbar: '#tt_employee',

    })

    /* Add Emp Dialog 弹窗*/
    $('#employee_add').dialog({
        title: '新增员工',
        width: 350,
        iconCls: 'icon-employee-add',
        buttons: [
            {
                text: 'Save',
                iconCls: 'icon-employee-submit',
                handler: function(){
                    if ($('#employee_add').form('validate')){
                        $.ajax({
                            type: 'post',
                            url: '/admin/addEmployees',
                            dataType: 'json',
                            data: {
                                employeeName: $('input[name="employeeName"]').val(),
                                department: $('input[name="department"]').val(),
                                email: $('input[name="email"]').val(),
                                province: $('input[name="province"]').val(),
                                cityName: $('input[name="cityName"]').val()
                            },
                            success: function(data, res, state){
                                if(data>0){
                                    $.messager.show({
                                        title: 'Employee Add Info',
                                        msg: 'A new employee added successfully'
                                    })
                                }else {
                                    $.messager.alert('Alert Info', "Failed to add new employee, please try again!!", 'error');
                                }
                                $('#employee_add').form('reset').dialog('close');
                                $('#employee').datagrid('reload')

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
                iconCls: 'icon-employee-cancel',
                handler: function () {
                    $('#employee_add').form('reset').dialog('close');
                }
            }
        ],
        modal: true,
        closed: true,


    })

    /* Edit Emp Dialog 弹窗*/
    $('#employee_edit').dialog({
        title: '编辑员工',
        width: 350,
        iconCls: 'icon-employee-edit',
        buttons: [
            {
                text: 'Save',
                iconCls: 'icon-employee-submit',
                handler: function(){
                    if ($('#employee_edit').form('validate')){
                        $.ajax({
                            type: 'post',
                            url: '/admin/updateEmployeeById',
                            dataType: 'json',
                            data: {
                                id: $('input[name="employee_id"]').val(),
                                employeeName: $('input[name="employeeName_edit"]').val(),
                                department: $('input[name="department_edit"]').val(),
                                email: $('input[name="email_edit"]').val(),
                                province: $('input[name="province_edit"]').val(),
                                cityName: $('input[name="cityName_edit"]').val()
                            },
                            success: function (data, res, state) {
                                if (data>0){
                                    $.messager.show({
                                        title: 'Employee Edit Info',
                                        msg: 'An employee updated successfully'
                                    })
                                } else {
                                    $.messager.alert('Alert Info', "Employee Info Update Failed, Please Try Again!", 'error');
                                }

                                $('#employee_edit').form('reset').dialog('close');
                                $('#employee').datagrid('reload')
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
                iconCls: 'icon-employee-cancel',
                handler: function () {
                    $('#employee_edit').form('reset').dialog('close');
                }
            }
        ],
        modal: true,
        closed: true,


    })

    $('input[name="employeeName"]').validatebox({
        required: true,

    })
    $('input[name="department"]').validatebox({
        required: true,

    })
    $('input[name="email"]').validatebox({
        required: true,
        validType: 'email',

    })

    $('input[name="department_edit"]').validatebox({
        required: true,

    })
    $('input[name="email_edit"]').validatebox({
        required: true,
        validType: 'email',

    })


})

/* 点击Add button 弹出dialog*/
function emp_add() {
    $('#employee_add').dialog('open');
}

/* Search Employee*/
function Search() {
    $('#employee').datagrid('load',{
        employeeName: $('input[name="empName"]').val()
    })
}
/* Edit employee*/
function emp_edit() {
    var rows=$('#employee').datagrid('getSelections');
    if (rows.length<1){
        $.messager.alert('Alert Info', 'Please select the record you want to edit firstly!','warning')
    }else if (rows.length===1){
        $.ajax({
            type: 'get',
            url: '/admin/getEmployeeById',
            dataType: 'json',
            data: {
                Id: rows[0].id
            },
            success: function (data, res, state) {
                if (data){
                    $('#employee_edit').form('load',{
                        employee_id: data.id,
                        employeeName_edit: data.employeeName,
                        department_edit: data.department,
                        email_edit: data.email,
                        province_edit: data.address.province,
                        cityName_edit: data.address.cityName
                    }).dialog('open');
                }else {
                    $.messager.alert("Retrieve Failure","Retrieve Info Failed!",'error')
                }
            },
            error: function () {
                $.messager.alert("Alert Info","Server Request Failed!",'error')
            }
        })

    } else {
        $.messager.alert('Alert Info', 'You could only select one record to edit at most once time','warning')
    }
}

/* Emp Delete */
function emp_delete() {
    var rows=$('#employee').datagrid('getSelections');
    if (rows.length>0){
        $.messager.confirm("Confirm Info", "Do you want to delete the selected row(s)?",function(r){
            if (r){
                var ids=[];
                for (var i=0; i<rows.length; i++){
                    ids.push(rows[i].id)
                }

                $.ajax({
                    type: 'post',
                    url: '/admin/deleteEmployees',
                    dataType: 'json',
                    data: {
                        Ids: ids.join(',')
                    },
                    success: function (data, res, state) {
                        if (data>0){
                            $.messager.show({
                                title: 'Employee Delete Info',
                                msg: 'An employee deleted successfully'
                            });
                            $('#employee').datagrid('reload')
                        }else {
                            $.messager.alert("Alert Info","Employee Info Delete Failed!",'error')
                        }
                    },
                    error: function () {
                        $.messager.alert("Alert Info","Server Request Failed!",'error')
                    }
                })
            }else {
                $('#employee').datagrid('unselectAll')
            }
        })
    }else {
        $.messager.alert("Alert Info","Please select a row or rows you want to delete",'info')
    }
}