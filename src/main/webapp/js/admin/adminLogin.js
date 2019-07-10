$(document).ready(function() {

    $('#login').dialog({
        title: '管理员登录',
        width: 300,
        height: 180,
        modal: true,
        closable: false,
        iconCls: 'icon-login',
        buttons: '#btn',

    })

    /*管理员 account validation 验证*/
    $('#account').validatebox({
        required: true,
        missingMessage: '管理员账号不能为空'

    })
    /* 管理员password validation 验证*/
    $('#password').validatebox({
        required: true,
        missingMessage: '管理员密码不能为空',
        validType: 'length[6,15]'

    })

    /*加载时设置焦点*/
    if (!$('#account').validatebox('isValid')) {
        $('#account').focus()
    }
    else {
        $('#password').focus();
    }


    $('#btn a').click(function () {
        if (!$('#account').validatebox('isValid')) {
            $('#account').focus()
        } else if (!$('#password').validatebox('isValid')) {
            $('#password').focus();
        } else {
            $.ajax({
                type: 'POST',
                url: '/admin/checkAdmin',
                data: {
                    account: $('#account').val(),
                    password: $('#password').val()
                },
                success: function (data) {
                    if (data == "-1") {
                        $.messager.alert('登录失败', '管理员账户不存在, 请重新输入', 'error')
                    } else if (data == "0") {
                        $.messager.alert('登录失败', '管理员密码输入错误, 请重新输入', 'error')
                    } else {
                        location.href = "/admin/adminIndex";
                    }
                },
                error: function () {
                    alert('Ajax 异步加载失败')
                }
            })
        }
    })

})

