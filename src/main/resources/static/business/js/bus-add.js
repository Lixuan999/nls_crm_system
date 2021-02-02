layui.use(['form', 'layer', 'laydate', 'table'], function () {

    var form = layui.form;
    var $ = layui.jquery;
    var laydate = layui.laydate;


    //日期
    laydate.render({
        elem: '#date'
    });


    //监听提交
    form.on('submit(add)', function (data) {
        console.log(data.field);

        layer.confirm("确认要添加吗?", {
            yes: function (index, layero) {

                $.ajax({
                    type: "POST",
                    url: "/bus/busAdd",
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