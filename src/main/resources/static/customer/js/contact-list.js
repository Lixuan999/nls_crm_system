layui.config({
    base: "js/"
}).use(['table', 'layer', 'form', 'jquery'], function () {
    var table = layui.table,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    var layerIndex = layer.load(3);

    table.render({
        elem: '#valueTable'  //要和table标签ID一致
        , url: '/cont/contact'
        , id: 'testReload'
        , toolbar: '#toolbar'
        , page: true
        , limit: 10
        , loading: true
        , cols: [[

            //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'contactId', title: 'ID', align: 'center'}
            , {field: 'way', title: '联系途径', align: 'center'}
            , {field: 'content', title: '聊天内容', align: 'center'}
            , {field: 'empName', templet: '<div>{{d.employeeList[0].empName}}</div>', title: '负责人', align: 'center'}
            , {
                field: 'customerName',
                templet: '<div>{{d.customerList[0].customerName}}</div>',
                title: '客户名称',
                align: 'center'
            }
            , {field: 'createTime', title: '创建时间', align: 'center'}
            , {field: 'updateTime', title: '修改时间', align: 'center'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', align: 'center'}
        ]]

        , limit: 10
        , limits: [10, 20, 25, 50, 100]
        , parseData: function (data) {
            //打印数据
            console.log(data)
        }
        //开启分页
        , page: true
        , where: {isDel: 0}
        , id: 'listReload'
    });
    layer.close(layerIndex);


    /**
     * 批量删除
     * @type {pe|jQuery|HTMLElement}
     */
    var $ = layui.$, active = {
        /**
         * 批量删除链接
         *
         * */
        closeBtn: function () {
            var $checkbox = $('table input[type="checkbox"][name="layTableCheckbox"]');
            var $checked = $('table input[type="checkbox"][name="layTableCheckbox"]:checked');
            if ($checkbox.is(":checked")) {
                var checkStatusId = table.checkStatus('listReload'),
                    data = checkStatusId.data,
                    contactId = [];

                for (var i in data) {
                    contactId.push(data[i].contactId)
                }

                layer.confirm('确定要删除' + data.length + '条数据吗?', {title: '系统信息'}, function (index) {
                    var layerIndex = layer.load(3);

                    $.ajax({
                        url: '/cont/delBatch',
                        type: 'PUT',
                        data: {
                            isDel: 1,
                            contactId: contactId
                        },
                        success: function (res) {
                            if (res.code == 200) {
                                table.reload('listReload', {});
                                $.message({
                                    message: res.msg,
                                    type: 'success',
                                    showClose: true
                                });
                                /*   window.parent.location.reload();   // 重新刷新表格数据*/

                            } else {
                                $.message({
                                    message: res.msg,
                                    type: 'warning',
                                    showClose: true
                                });
                            }
                        }, error: function (data) {
                            $.message({
                                message: '系统异常..',
                                type: 'error',
                                showClose: true
                            });
                        }, complete: function () {
                            layer.close(index);
                            layer.close(layerIndex);
                        }
                    })


                })
            } else {
                $.message({
                    message: '请选中要删除的数据',
                    type: 'warning',
                    showClose: true
                });
            }
        },

    };
    $('.batchDel').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //监听删除操作
    table.on('tool(employeeManage)', function (obj) {
        var layerIndex = layer.load(3);

        var data = obj.data;
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm('确定删除该条客户跟踪信息吗？', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/cont/contDelete",
                    data: {
                        contactId: data.contactId
                    },
                    success: function (data) {
                        if (data.code == 200) {
                            obj.del();
                            layer.close(index);
                            $.message({
                                message: "操作成功",
                                type: 'success',
                                showClose: true
                            });
                        } else {
                            $.message({
                                message: "系统匆忙，请销后再试 !",
                                type: 'warning',
                                showClose: true
                            });
                        }
                    }

                });
            });

        } else if (obj.event === 'edit') {

            editEmployee(data);
        }
        layer.close(layerIndex);

    });


    table.on('toolbar(employeeManage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'add') {
            addEmployee(data)
        }
    });


});


//编辑
function editEmployee(data) {
    var index = layui.layer.open({
        title: "编辑",
        type: 2,
        content: "cont-edit.html",//弹出层页面
        area: ['100%', '100%'],
        success: function (layer, index) {
            var body = layui.layer.getChildFrame('body', index);
            if (data) {
                // 取到弹出层里的元素，并把编辑的内容放进去 数据回显
                body.find(".contactId").val(data.contactId);
                body.find(".way").val(data.way);
                body.find(".content").val(data.content);

                // 给select标签赋值value。
                body.find("#empName").val(data.employeeId);
                // 根据id选择那一项
                body.find("#empName option").eq(data.employeeId).attr("selected");

                // 给select标签赋值value。
                body.find("#customerName").val(data.cusId);
                // 根据id选择那一项
                body.find("#customerName option").eq(data.cusId).attr("selected");

                // 记得重新渲染表单
                form.render();
            }
            setTimeout(function () {
                layui.layer.tips('点击此处返回客户跟踪列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            }, 500)
        }
    })
    //layui.layer.full(index);//设置弹出层布满窗口
    //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    $(window).on("resize", function () {
        //layui.layer.full(window.sessionStorage.getItem("index"));
    })
}


function addEmployee(data) {
    var index = layui.layer.open({
        title: "添加",
        type: 2,
        content: "./cont-add.html",//弹出层页面
        area: ['600px', '300px'],
        success: function (layer, index) {
            var body = layui.layer.getChildFrame('body', index);
            if (data) {
                // 取到弹出层里的元素，并把编辑的内容放进去
                // 给select标签赋值value。
                body.find("#classesName").val(data.classesId);
                // 根据id选择那一项
                // 记得重新渲染表单
                form.render();
            }
            setTimeout(function () {
                layui.layer.tips('点击此处返回客户跟踪列表', '.layui-layer-setwin .layui-layer-close', {
                    tips: 3
                });
            }, 500)
        }
    })

    //layui.layer.full(index);//设置弹出层布满窗口
    //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
    $(window).on("resize", function () {
        layui.layer.full(window.sessionStorage.getItem("index"));
    })
}
