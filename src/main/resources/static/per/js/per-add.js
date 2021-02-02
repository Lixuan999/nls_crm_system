layui.use(['form', 'layer'], function () {

    var $ = layui.jquery;
    var form = layui.form,
        layer = layui.layer;


    //监听提交
    form.on('submit(add)', function (data) {
        console.log(data.field);

        layer.confirm("确认要添加吗?", {
            yes: function (index, layero) {

                $.ajax({
                    type: "POST",
                    url: "/per/perAdd",
                    data: data.field,
                    datatype: "json",
                    success: function (data) {
                        console.log(data);
                        if (data.code == 200) {
                            $.message({
                                message: "添加成功",
                                type: 'success',
                                showClose: true
                            });
                            //操作成功后页面2秒后刷新
                            setTimeout(function () {
                                layer.closeAll("iframe");
                                parent.location.reload();
                            }, 2000);
                        }

                        if (data.code == 100) {
                            $.message({
                                message: data.msg,
                                type: 'warning',
                                showClose: true
                            });
                            return false;
                        }

                    },
                    error: function () {
                        $.message({
                            message: '请求异常',
                            type: 'error',
                            showClose: true
                        });
                    }

                });
                layer.close(index);
            }
        })
        return false;
    });


    //监听权限多选框全选与全不全
    form.on('checkbox(father)', function (data) {

        if (data.elem.checked) {
            $(data.elem).parent().siblings('td').find('input').prop("checked", true);
            form.render();
        } else {
            $(data.elem).parent().siblings('td').find('input').prop("checked", false);
            form.render();
        }
    });


});