layui.use(['form', 'layer'], function () {
    var $ = layui.jquery;
    var form = layui.form,
        layer = layui.layer;


    //查询数据库中的角色
    $(function () {
        $.ajax({
            type: "GET",
            url: "/rol/goRole",
            datatype: "json",
            success: function (data) {
                console.log(data);
                $.each(data, function (index, item) {
                    $('#rName').append(new Option(item.rName, item.rolId));// 下拉菜单里添加元素
                });

                layui.form.render("select");
            }, error: function () {
                alert("查询角色失败！")
            }
        });
    })


    //监听提交
    form.on('submit(edit)', function (data) {
        console.log(data.field);

        layer.confirm("确认要修改吗?", {
            yes: function (index, layero) {

                $.ajax({
                    url: "/dep/depUpdate",
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