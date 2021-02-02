layui.use(['form', 'layer'], function () {
    $ = layui.jquery;
    var form = layui.form
        , layer = layui.layer;


    //查询负责人
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
                    $('#ename').append(new Option(item.ename, item.eid));// 下拉菜单里添加元素
                });

                layui.form.render("select");
            }, error: function () {
                alert("查询负责人失败！")
            }
        });
    })


    //查询客户
    $(function () {
        $.ajax({
            //提交数据的类型 POST GET
            type: "GET",
            //提交的网址
            url: "/cus/goCustomer",
            //提交的数据
            //返回数据的格式
            datatype: "json",
            //成功返回之后调用的函数
            success: function (data) {
                console.log(data);
                $.each(data, function (index, item) {
                    $('#customerName').append(new Option(item.customerName, item.customerId));// 下拉菜单里添加元素
                });

                layui.form.render("select");
            }, error: function () {
                alert("查询客户名称失败!")
            }
        });
    })

    //监听提交
    form.on('submit(edit)', function (data) {
        console.log(data.field);

        layer.confirm("确认要修改吗?", {
            yes: function (index, layero) {

                $.ajax({
                    url: "/cont/contUpdate",
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