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

    var layerIndex = layer.load(3, {time: 5 * 1000});

    /**
     * 查询角色 并回显
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

    /**
     * 查询员工信息数据回显
     */
    $.ajax({
        type: "Get",
        url: "/emp/getShiroUserInfo",
        datatype: "json",
        success: function (data) {
            console.log(data)
            if (data.code == 200) {
                $('.employeeId').val(data.data.employeeId);
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
                            $("#rname").each(function () {
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

    layer.close(layerIndex);
})
