layui.use(['form', 'layer'], function () {
    $ = layui.jquery;
    var form = layui.form
        , layer = layui.layer;


    //监听提交
    form.on('submit(edit)', function (data) {
        console.log(data.field);

        layer.confirm("确认要修改吗?", {
            yes: function (index, layero) {

                $.ajax({
                    url: "/bus/busUpdate",
                    type: 'PUT',
                    dataType: 'json',
                    data: data.field,
                    success: function (data) {
                        console.log(data);

                        if (data.code == 200) {
                            $.message({
                                message: "修改成功",
                                type: 'success',
                                showClose: true
                            });
                        }
                        //操作成功后页面2秒后刷新
                        setTimeout(function () {
                            layer.closeAll("iframe");
                            parent.location.reload();
                        }, 2000);

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
                            message: '请求异常!',
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


});