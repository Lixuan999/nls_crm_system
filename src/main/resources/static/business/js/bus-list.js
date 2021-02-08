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
        , url: '/bus/business'
        , id: 'reload'
        , toolbar: '#toolbar'
        , page: true
        , limit: 10
        , loading: true
        , cols: [[

            //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'businessId', title: 'ID', align: 'center', sort: true}
            , {field: 'bname', title: '业务名称', align: 'center'}
            , {field: 'head', title: '负责人', align: 'center'}
            , {field: 'phone', title: '电话号码', align: 'center'}
            , {field: 'description', title: '描述', align: 'center'}
            , {field: 'createTime', title: '创建时间', align: 'center', sort: true}
            , {field: 'updateTime', title: '修改时间', align: 'center', sort: true}
            , {fixed: 'right', align: 'center', toolbar: '#barDemo', title: '操作', align: 'center'}
        ]]

    });
    layer.close(layerIndex);

    //监听删除操作
    table.on('tool(employeeManage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm('确定删除该条业务信息吗？', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/bus/busDelete",
                    data: {
                        businessId: data.businessId
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
     * 编辑
     */
    function editEmployee(data) {
        var index = layui.layer.open({
            title: "编辑",
            type: 2,
            content: "bus-edit.html",//弹出层页面
            area: ['1320px', '645px'],
            success: function (layer, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (data) {
                    // 取到弹出层里的元素，并把编辑的内容放进去 数据回显
                    body.find(".businessId").val(data.businessId);
                    body.find(".bName").val(data.bname);
                    body.find(".head").val(data.head);
                    body.find(".phone").val(data.phone);
                    body.find(".description").val(data.description);


                    // 给select标签赋值value。
                    body.find("#permName").val(data.roleId);

                    // 根据id选择那一项
                    body.find("#permName option").eq(data.roleId).attr("selected");

                    // 记得重新渲染表单
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回业务列表', '.layui-layer-setwin .layui-layer-close', {
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
                url: '/bus/delete',
                data: {
                    isDel: 1,
                    businessId: data.elem.getAttribute("switchId")
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
                url: '/bus/delete',
                data: {
                    isDel: 0,
                    businessId: data.elem.getAttribute("switchId")
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

    /**
     * 添加
     * @param data
     */
    function addEmployee(data) {
        var index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "./bus-add.html",//弹出层页面
            area: ['100%', '100%'],
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
                    layui.layer.tips('点击此处返回业务列表', '.layui-layer-setwin .layui-layer-close', {
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