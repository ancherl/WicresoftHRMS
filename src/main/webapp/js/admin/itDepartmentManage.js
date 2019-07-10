$(document).ready(function () {
    $('#itDepartment').datagrid({
        title: '部门列表',
        url: '/admin/ajaxLoadITDepts',
        iconCls:'icon-IT',
        columns: [[
            {
                field: 'id',
                title: 'ID',
                checkbox: true
            },
            {
                field: 'departmentName',
                title: '部门名称',
                sortable: true
            },
            {
                field: 'location',
                title: '地点',
                sortable: true
            },
            {
                field: 'departmentManager',
                title: '部门经理',
                sortable: true
            }
        ]],
        pagination: true,
        rownumbers: true,
        striped: true,
        pageSize: 5,
        pageList: [2,5,10,20],
        fitColumns: true,
        toolbar: '#tt_itDepartment',
    })
})