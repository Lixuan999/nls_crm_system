layui.use(['form', 'layer'], function () {
    $ = layui.jquery;
    var form = layui.form
        , layer = layui.layer;


    //监听提交
    form.on('submit(edit)', function (data) {
        console.log(data.field);
        layer.alert("保存成功", {icon: 6}, function () {
            // 获得frame索引
            var index = parent.layer.getFrameIndex(window.name);
            //关闭当前frame
            parent.layer.close(index);
            $.ajax({
                type: "PUT",
                url: "/con/conUpdate",
                data: data.field,
                datatype: "json",
                //成功返回之后调用的函数
                success: function (data) {
                    console.log(data);
                    if (data.code == 200) {
                        layer.close(index);
                        layer.msg('修改成功了哦', {icon: 6, shift: 6});
                        window.parent.location.reload();  // 重新刷新表格数据
                    } else {
                        layer.msg("修改失败", {icon: 5});
                    }
                }
            });
        });
        return false;
    });


});