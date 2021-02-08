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
        , url: '/ord/orders'
        , id: 'reload'
        , toolbar: '#toolbar'
        , page: true
        , loading: true
        , cols: [[

            //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'ordersId', title: 'ID', align: 'center', sort: true}
            , {field: 'totalPrice', title: '共价', align: 'center', sort: true}
            , {
                field: 'customerName',
                templet: '<div>{{d.customerList[0].customerName}}</div>',
                title: '客户姓名',
                align: 'center'
            }
            , {
                field: 'phone',
                templet: '<div>{{d.customerList[0].phone}}</div>',
                title: '手机',
                align: 'center'
            }
            , {
                field: 'address',
                templet: '<div>{{d.customerList[0].address}}</div>',
                title: '地址',
                align: 'center'
            }
            , {
                field: 'bname',
                templet: '<div>{{d.businessList[0].bname}}</div>',
                title: '业务名称',
                align: 'center'
            }
            , {field: 'createTime', title: '下单时间', align: 'center', sort: true}
            , {field: 'updateTime', title: '修改时间', align: 'center', sort: true}
            , {fixed: 'right', align: 'center', toolbar: '#barDemo', title: '操作', align: 'center'}

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
                    ordersId = [];

                for (var i in data) {
                    ordersId.push(data[i].ordersId)
                }

                layer.confirm('确定要删除' + data.length + '条数据么?', {title: '系统信息'}, function (index) {
                    var layerIndex = layer.load(3);
                    $.ajax({
                        url: '/ord/delBatch',
                        type: 'PUT',
                        data: {
                            isDel: 1,
                            ordersId: ordersId
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
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm('确定删除该条订单信息吗？', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/ord/ordDelete",
                    data: {
                        ordersId: data.ordersId
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

    });


    table.on('toolbar(employeeManage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'add') {
            addEmployee(data)
        }
    });


    /**
     * 监听开关 状态 操作
     */
    form.on('switch(switchTest)', function (data) {
        /**
         * 禁用标签
         * 状态 赋值为 1
         */
        var layerIndex = layer.load(3);

        if ((this.checked ? 'true' : 'false') == 'false') {

            $.ajax({
                url: '/ord/delete',
                data: {
                    isDel: 1,
                    ordersId: data.elem.getAttribute("switchId")
                },
                type: 'PUT', //HTTP请求类型
                success: function (data) {
                    $.message({
                        message: "禁用成功",
                        type: 'success',
                        showClose: true
                    });

                }, error: function () {
                    $.message({
                        message: "boom..",
                        type: 'error',
                        showClose: true
                    });
                }

            })
        } else {
            /**
             * 启动标签
             * 状态 赋值为 0
             */
            $.ajax({
                url: '/ord/delete',
                data: {
                    isDel: 0,
                    ordersId: data.elem.getAttribute("switchId")
                },
                type: 'PUT',
                success: function (data) {
                    $.message({
                        message: "启动成功",
                        type: 'success',
                        showClose: true
                    });

                }, error: function () {
                    $.message({
                        message: "boom..",
                        type: 'error',
                        showClose: true
                    });
                }

            })
        }
        layer.close(layerIndex);

    });


    //编辑
    function editEmployee(data) {
        var index = layui.layer.open({
            title: "编辑",
            type: 2,
            content: "ord-edit.html",//弹出层页面
            area: ['100%', '100%'],
            success: function (layer, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (data) {
                    // 取到弹出层里的元素，并把编辑的内容放进去 数据回显
                    body.find(".ordersId").val(data.ordersId);
                    body.find(".totalPrice").val(data.totalPrice);

                    // 给select标签赋值value。
                    body.find("#bname").val(data.busId);
                    // 根据id选择那一项
                    body.find("#bname option").eq(data.busId).attr("selected");


                    // 给select标签赋值value。
                    body.find("#customerName").val(data.cusId);
                    // 根据id选择那一项
                    body.find("#customerName option").eq(data.cusId).attr("selected");

                    // 记得重新渲染表单
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回订单列表', '.layui-layer-setwin .layui-layer-close', {
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
            content: "./ord-add.html",//弹出层页面
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
                    layui.layer.tips('点击此处返回订单列表', '.layui-layer-setwin .layui-layer-close', {
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

});



