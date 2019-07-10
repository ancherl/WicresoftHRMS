function uploadPhoto() {
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

    $('#formPhoto').ajaxSubmit({
        type: 'POST',
        url: '/Emp/ajaxUploadImages',
        success: function (data) {
            $('#imgDiv').empty();
            $('#imgDiv').html('<img src="'+data+'">')
        },
        error: function () {
            alert('上传失败，请检查网络后重试!');
        }
    })

}
