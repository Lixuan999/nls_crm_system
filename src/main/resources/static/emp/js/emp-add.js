layui.use(['form', 'layer'], function () {
    $ = layui.jquery;
    var form = layui.form
        , layer = layui.layer;

    var layerIndex = layer.load(3, {time: 5 * 3000});
    /**
     *  查询数据库中的角色
     */
    $.ajax({
        type: "GET",
        url: "/rol/goRole",
        datatype: "json",
        success: function (data) {
            console.log(data);
            $.each(data, function (index, item) {
                $('#rname').append(new Option(item.rname, item.rolId));// 下拉菜单里添加元素
            });

            layui.form.render("select");
        }, error: function () {
            alert("查询角色失败！！！")
        }
    });


    //监听提交
    form.on('submit(add)', function (data) {
        console.log(data.field);

        layer.confirm("确认要添加吗?", {
            yes: function (index, layero) {

                $.ajax({
                    type: "POST",
                    url: "/emp/empAdd",
                    data: data.field,
                    datatype: "json",
                    success: function (data) {
                        console.log(data);
                        if (data.code == 100) {
                            $.message({
                                message: data.msg,
                                type: 'warning',
                                showClose: true
                            });
                            return false;
                        }
                        //操作成功后页面2秒后刷新
                        setTimeout(function () {
                            layer.closeAll("iframe");
                            parent.location.reload();
                        }, 2000);

                        $.message({
                            message: data.msg,
                            type: 'success',
                            showClose: true
                        });

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

    layer.close(layerIndex);

})
