$(document).ready(function () {
    $('#pcResource').datagrid({
        title: 'PC资源列表',
        url: '/admin/ajaxLoadPCResources',
        iconCls: 'icon-pc',
        columns: [[
            {
                field: 'id',
                title: 'ID',
                checkbox: true
            },
            {
                field: 'imageUrl',
                title: '图片预览',
                formatter: function (value, rowData, rowIndex) {
                    return '<img src="http://localhost:8080/' +value +'" style="width: 80px;height: 80px" />';
                }

            },
            {
                field: 'brand',
                title: '品牌',
                sortable: true
            },
            {
                field: 'processor',
                title: '处理器',
                sortable: true
            },
            {
                field: 'memory',
                title: '内存',
                sortable: true
            },
            {
                field: 'serialNumber',
                title: '序列码'
            }
        ]],
        pagination: true,
        rownumbers: true,
        striped: true,
        pageSize: 5,
        pageList: [2,5,10,20],
        fitColumns: true,
        toolbar: '#tt_pcResource'
    })

    /* PC Add Dialog */
    $('#pcs_add').dialog({
        title: '新增 PC',
        width: 435,
        iconCls: 'icon-pc',
        buttons: [
            {
                text: 'Save',
                iconCls: 'icon-employee-submit',
                handler: function(){
                    if ($('#pcs_add').form('validate')) {
                        var imagePath=$('#image_input').val();
                        if (imagePath===''){
                            alert('请选择你要上传的文件!');
                            return false;
                        }

                        var strExtension=imagePath.substr(imagePath.lastIndexOf('.')+1);
                        if (strExtension!=='jpg'&& strExtension!=='png' && strExtension!=='gif' && strExtension!=='bmp') {
                            alert('请选择一个image格式的文件!');
                            return false;
                        }

                        if (tempFile!==undefined){
                            $.ajax({
                                type: 'post',
                                url: '/admin/addNewPCResource1',
                                dataType: 'json',
                                data: {
                                    brand: $('input[name="brand"]').val(),
                                    processor: $('input[name="processor"]').val(),
                                    memory: $('input[name="memory"]').val(),
                                    serialNumber: $('input[name="serialNumber"]').val(),
                                    temporaryFile: tempFile
                                },
                                success: function (data, res, state) {
                                    if (data===1){
                                        $.messager.show({
                                            title: 'PC Resource Add Info',
                                            msg: 'A new pc added successfully'
                                        })
                                    } else {
                                        $.messager.alert('Alert Info', "Failed to add new pc resource, please try again!!", 'error');
                                    }

                                    $('#imgPreview').empty();
                                    $('#pcs_add').form('reset').dialog('close');
                                    $('#pcResource').datagrid('reload')
                                },
                                error: function () {
                                    alert('Ajax请求失败，请检查网络后重试!');
                                }
                            })
                        } else {
                            $('#pcs_add').ajaxSubmit({
                                type: 'post',
                                url: '/admin/addNewPCResource',
                                success: function (data) {
                                    if (data===1){
                                        $.messager.show({
                                            title: 'PC Resource Add Info',
                                            msg: 'A new pc added successfully'
                                        })
                                    } else {
                                        $.messager.alert('Alert Info', "Failed to add new pc resource, please try again!!", 'error');
                                    }

                                    $('#imgPreview').empty();
                                    $('#pcs_add').form('reset').dialog('close');
                                    $('#pcResource').datagrid('reload')
                                },
                                error: function () {
                                    alert('Ajax请求失败，请检查网络后重试!');
                                }
                            })
                        }
                    }

                }
            },
            {
                text: 'Cancel',
                iconCls: 'icon-employee-cancel',
                handler: function () {
                    $('#pcs_add').form('reset').dialog('close');
                    $('#imgPreview').empty();
                    deleteCachedPreFile();
                    tempFile=undefined
                }
            }
        ],
        modal: true,
        closed: true,
        closable: false
    })
    
    /* PC Edit dialog */
    $('#pcs_edit').dialog({
        title: '编辑 PC',
        width: 435,
        iconCls: 'icon-pc',
        buttons: [
            {
                text: 'Save',
                iconCls: 'icon-employee-submit',
                handler: function() {
                    /* 删除数据中原本保存的相应的图片*/
                    if (savedImgFile!==null) {
                        $.ajax({
                            type: 'post',
                            url: '/admin/deleteCachedPreImage',
                            dataType: 'json',
                            data: {
                                filePath: "/Users/daixin/JavaProject/WicreosoftHRMS/src/main/webapp/images/"+savedImgFile
                            }
                        })

                    };

                    if (tempFile!==undefined){
                        $.ajax({
                            type: 'post',
                            url: '/admin/updatePCRecord',
                            dataType: 'json',
                            data: {
                                id: $('input[name="pc_id"]').val(),
                                brand: $('input[name="brand_edit"]').val(),
                                processor: $('input[name="processor_edit"]').val(),
                                memory: $('input[name="memory_edit"]').val(),
                                serialNumber: $('input[name="serialNumber_edit"]').val(),
                                temporaryFile: tempFile
                            },
                            success: function (data, res, state) {
                                if (data===1){
                                    $.messager.show({
                                        title: 'PC Resource Update Info',
                                        msg: 'A new pc updated successfully'
                                    })
                                } else {
                                    $.messager.alert('Alert Info', "Failed to update new pc resource, please try again!!", 'error');
                                }

                                $('#imgPreview_edit').empty();
                                $('#pcs_edit').form('reset').dialog('close');
                                $('#pcResource').datagrid('reload')
                            },
                            error: function () {
                                alert('Ajax请求失败，请检查网络后重试!');
                            }
                        })
                    } else {
                        $('#pcs_edit').ajaxSubmit({
                            type: 'post',
                            url: '/admin/updatePCRecord1',
                            success: function (data) {
                                if (data===1){
                                    $.messager.show({
                                        title: 'PC Resource Update Info',
                                        msg: 'A new pc updated successfully'
                                    })
                                } else {
                                    $.messager.alert('Alert Info', "Failed to update new pc resource, please try again!!", 'error');
                                }

                                $('#imgPreview_edit').empty();
                                $('#pcs_edit').form('reset').dialog('close');
                                $('#pcResource').datagrid('reload')

                            },
                            error: function () {
                                alert('Ajax请求失败，请检查网络后重试!');
                            }
                        })
                    }
                }
            },
            {
                text: 'Cancel',
                iconCls: 'icon-employee-cancel',
                handler: function () {
                    $('#pcs_edit').form('reset').dialog('close');
                    deleteCachedPreFile();
                    tempFile=undefined
                }
            }
        ],
        modal: true,
        closed: true,
        closable: false
    })


    $('input[name="brand"]').validatebox({
        required: true,

    })
    $('input[name="brand_edit"]').validatebox({
        required: true,
    })
    $('input[name="processor"]').validatebox({
        required: true,

    })
    $('input[name="processor_edit"]').validatebox({
        required: true,

    })
    $('input[name="memory"]').validatebox({
        required: true,

    })
    $('input[name="memory_edit"]').validatebox({
        required: true,

    })
    $('input[name="serialNumber"]').validatebox({
        required: true,

    })
    $('input[name="serialNumber_edit"]').validatebox({
        required: true,

    })


})

var tempFile;
/* 保存在数据库中的image path*/
var savedImgFile;

/* 删除缓存的预览图片 */
function deleteCachedPreFile() {
    /* Delete Cached Preview Image File*/
    if (tempFile!==undefined){
        /* Send Delete Ajax Request */
        $.ajax({
            type: 'post',
            url: '/admin/deleteCachedPreImage',
            dataType: 'json',
            data: {
                filePath: tempFile
            },
            success: function (data, res, state) {
                if (data>0){
                    $('#imgPreview').empty();
                    $('#imgPreview_edit').empty()
                }else {
                    alert("缓存文件删除失败!");
                    return false;
                }
            },
            error: function () {
                alert("Server Request Failed, please check your network and try again!")
            }
        })
    }
}

function imgPre() {

    $('#imgPreview').empty();

    var imagePath=$('#image_input').val();
    if (imagePath===''){
        alert('请选择你要上传的文件!');
        return false;
    }

    var strExtension=imagePath.substr(imagePath.lastIndexOf('.')+1);
    if (strExtension!=='jpg'&& strExtension!=='png' && strExtension!=='gif' && strExtension!=='bmp') {
        alert('请选择一个image格式的文件!');
        return false;
    }


    deleteCachedPreFile();


    $('#pcs_add').ajaxSubmit({
        type: 'post',
        url: '/admin/previewImage',
        success: function (data) {
            $('#imgPreview').html('<img src="http://localhost:8080/'+data+'" style="width: 80px;height: 80px" />');

            tempFile = "/Users/daixin/JavaProject/WicreosoftHRMS/src/main/webapp/images/"+data;
        },
        error: function () {
            alert('文件上传失败，请检查网络后重试!');
        }
    })
}

function pc_add() {
    $('#pcs_add').dialog('open');
}

function imgPre_edit() {

    var imagePath=$('#image_input_edit').val();
    if (imagePath===''){
        alert('请选择你要上传的文件!');
        return false;
    }

    var strExtension=imagePath.substr(imagePath.lastIndexOf('.')+1);
    if (strExtension!=='jpg'&& strExtension!=='png' && strExtension!=='gif' && strExtension!=='bmp') {
        alert('请选择一个image格式的文件!');
        return false;
    }


    deleteCachedPreFile();


    $('#pcs_edit').ajaxSubmit({
        type: 'post',
        url: '/admin/previewImage',
        success: function (data) {
            $('#imgPreview_edit').empty().html('<img src="http://localhost:8080/'+data+'" style="width: 80px;height: 80px" />');

            tempFile = "/Users/daixin/JavaProject/WicreosoftHRMS/src/main/webapp/images/"+data;
        },
        error: function () {
            alert('文件上传失败，请检查网络后重试!');
        }
    })
}

function pc_edit() {
    var rows=$('#pcResource').datagrid('getSelections');
    if (rows.length<1){
        $.messager.alert('Alert Info', 'Please select the record you want to edit firstly!','warning')
    } else if (rows.length===1){
        $.ajax({
            type: 'get',
            url: '/admin/getPCRecordById',
            dataType: 'json',
            data: {
                Id: rows[0].id
            },
            success: function (data, res, state) {
                if (data){
                    /* 如果该记录在数据库中已经保存了相应的image url, 则将其获取并保存在 savedImageFile 变量中*/
                    savedImgFile=data.imageUrl;
                    $('#imgPreview_edit').empty().html('<img src="http://localhost:8080/'+data.imageUrl+'" style="width: 80px;height: 80px" />');
                    $('#pcs_edit').form('load',{
                        pc_id: data.id,
                        brand_edit: data.brand,
                        processor_edit: data.processor,
                        memory_edit: data.memory,
                        serialNumber_edit: data.serialNumber
                    }).dialog('open')
                }else {
                    $.messager.alert("Retrieve Failure","Could not find corresponding PC record!",'error')
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

function pc_delete() {
    var rows=$('#pcResource').datagrid('getSelections');
    if (rows.length>0){
        $.messager.confirm("Confirm Info", "Do you want to delete the selected row(s)?", function (r) {
            if (r){
                var ids=[];
                for (var i=0; i<rows.length; i++){
                    ids.push(rows[i].id);
                }

                $.ajax({
                    type: 'post',
                    url: '/admin/deletePCRecords',
                    dataType: 'json',
                    data: {
                        Ids: ids.join(',')
                    },
                    success: function (data, res, state) {
                        if (data===1){
                            $.messager.show({
                                title: 'PC Record Delete Info',
                                msg: 'PC Record(s) deleted successfully'
                            })
                        } else {
                            $.messager.alert("Alert Info","PC Record(s) Delete Failed!",'error')
                        }

                        $('#pcResource').datagrid('reload')
                    },
                    error: function () {
                        $.messager.alert("Alert Info","Server Request Failed!",'error')
                    }
                })
            }
        })
    } else {
        $.messager.alert("Alert Info","Please select a row or rows you want to delete",'info')
    }
}