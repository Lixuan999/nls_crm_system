var $;
layui.config({
    base: "js/"
}).use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer;
    $ = layui.jquery;

    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }

    var layerIndex = layer.load(3, {time: 5 * 3000});
    /**
     * 查询角色
     */
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
            alert("查询角色失败！！！")
        }
    });


    /**
     * 员工修改监听数据回显
     */
    $.ajax({
        type: "POST",
        url: "/emp/queryEmpInfo",
        data: {
            employeeId: $.getUrlParam("employeeId")
        },
        datatype: "json",
        success: function (data) {
            console.log(data)
            if (data.code == 200) {
                $('.employeeId').val(data.data.employeeId);
                $('.accountName').val(data.data.accountName);
                $('.empName').val(data.data.empName);
                $('.age').val(data.data.age);
                $('.sex').val(data.data.sex);
                $('.phone').val(data.data.phone);
                $('.address').val(data.data.address);
                $("input[type=radio][name='sex'][value='" + data.data.sex + "']").attr("checked", 'checked');

                /**
                 * 查询角色数据回显
                 */
                $.ajax({
                    type: "POST",
                    url: "/rol/queryEmpRoleInfo",
                    datatype: "json",
                    data: {
                        employeeId: data.data.employeeId
                    },
                    success: function (data) {
                        console.log(data)
                        if (data.code == 200) {
                            $("#rName").each(function () {
                                $(this).children("option").each(function () {
                                    if (this.value == data.data.roleId) {
                                        $(this).attr("selected", "selected");
                                        form.render("select");
                                    }
                                    if (data.data.roleId != 1) {
                                        $(this).attr("disabled", 'disabled')
                                    }
                                });
                            })

                        }
                      }, complete: function (XHR, TS) {

                    }
                });
                form.render();
            }
        }
    });


    //监听提交
    form.on('submit(edit)', function (data) {
        console.log(data.field);

        layer.confirm("确认要修改吗?", {
            yes: function (index, layero) {

                $.ajax({
                    url: "/emp/empUpdate",
                    type: 'PUT',
                    dataType: 'json',
                    data: data.field,
                    success: function (data) {
                        console.log(data);

                        if (data.code == 200) {
                            $.message({
                                message: data.msg,
                                type: 'success',
                                showClose: true
                            });
                            //操作成功后页面2秒后刷新
                            setTimeout(function () {
                                layer.closeAll("iframe");
                                parent.location.reload();
                            }, 2000);
                            return false;

                        } else {
                            $.message({
                                message: "修改失败",
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
    layer.close(layerIndex);

});
