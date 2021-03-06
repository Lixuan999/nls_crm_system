layui.use(['form', 'layer'], function () {

    $ = layui.jquery;
    var form = layui.form
        , layer = layui.layer;

    $(function () {
        $.ajax({
            //提交数据的类型 POST GET
            type: "GET",
            //提交的网址
            url: "/emp/goEmployee",
            //提交的数据
            //返回数据的格式
            datatype: "json",
            //成功返回之后调用的函数
            success: function (data) {
                console.log(data);
                $.each(data, function (index, item) {
                    $('#empName').append(new Option(item.empName, item.employeeId));// 下拉菜单里添加元素
                });

                layui.form.render("select");
            }, error: function () {
                alert("查询负责人失败！！！")
            }
        });
    })
    url: "/cus/cusAdd",
    //监听提交
    form.on('submit(add)', function (data) {
        console.log(data.field);

        layer.confirm("确认要添加吗?", {
            yes: function (index, layero) {

                $.ajax({
                    type: "POST",

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


});