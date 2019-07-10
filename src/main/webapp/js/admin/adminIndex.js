$(document).ready(function () {
    $('#tabs').tabs({
        fit: true,
        border: false,

    })

    /* menu options load from server*/
    $('#nav').tree({
        url: "/admin/menuOptions",
        lines: true,
        onLoadSuccess: function (node, data) {
            if (data){
                $.each(data, function (index, value) {
                    if (this.state=="closed"){
                        $('#nav').tree('expandAll')
                    }
                })
                /* 等同于 $('nav').tree('expandAll')*/
            }
        },
        onClick: function(node){
            if (node.url){
                if ($('#tabs').tabs('exists',node.text)){
                    $('#tabs').tabs('select', node.text)
                } else {
                    $('#tabs').tabs('add', {
                        title: node.text,
                        iconCls: node.iconCls,
                        closable: true,
                        href: '/admin/'+node.url
                    })
                }

            }
        }
    })
})