layui.use(['form', 'layer'], function () {

    let $ = layui.jquery;
    const form = layui.form
        , layer = layui.layer;

    $(function () {
        $.ajax({
            //提交数据的类型 POST GET
            type: "GET",
            //提交的网址
            url: "/per/goPerm",
            //提交的数据
            //返回数据的格式
            datatype: "json",
            //成功返回之后调用的函数
            success: function (data) {
                console.log(data);
                $.each(data, function (index, item) {
                    $('#permName').append(new Option(item.permName, item.permissionId));// 下拉菜单里添加元素
                });

                layui.form.render("select");
            }, error: function () {
                alert("查询权限字符失败！！！")
            }
        });
    })

    $(function () {
        $.ajax({
            //提交数据的类型 POST GET
            type: "GET",
            //提交的网址
            url: "/dep/goDepartment",
            //提交的数据
            //返回数据的格式
            datatype: "json",
            //成功返回之后调用的函数
            success: function (data) {
                console.log(data);
                $.each(data, function (index, item) {
                    $('#dName').append(new Option(item.dName, item.departmentId));// 下拉菜单里添加元素
                });

                layui.form.render("select");
            }, error: function () {
                alert("查询所属部门失败！！！")
            }
        });
    })

    //监听提交
    form.on('submit(add)', function (data) {
        console.log(data.field);

        layer.confirm("确认要添加吗?", {
            yes: function (index, layero) {

                $.ajax({
                    type: "POST",
                    url: "/rol/rolAdd",
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
                            //操作成功后页面1秒后刷新
                            setTimeout(function () {
                                layer.closeAll("iframe");
                                parent.location.reload();
                            }, 1000);
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