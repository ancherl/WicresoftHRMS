$(document).ready(function () {
    $('#hrDepartment').datagrid({
        title: 'HR 列表',
        url: '/admin/ajaxLoadHRDepts',
        iconCls: 'icon-HR',
        columns: [[
            {
                field: 'id',
                title: 'ID',
                checkbox: true
            },
            {
                field: 'departmentName',
                title: 'DepartmentName',
                sortable: true
            },
            {
                field: 'manager',
                title: 'Manager',
                sortable: true
            }
        ]],
        pagination: true,
        rownumbers: true,
        striped: true,
        pageSize: 5,
        pageList: [2,5,10,20],
        fitColumns: true,
        toolbar: '#tt_hrDepartment',
    })
})