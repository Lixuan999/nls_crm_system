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
        , url: '/rol/role'
        , id: 'reload'
        , toolbar: '#toolbar'
        , page: true
        , limit: 10
        , loading: true
        , cols: [[

            //表头
            {type: 'checkbox', fixed: 'left'}
            , {type: 'numbers', title: '序号', align: 'center'}
            , {field: 'rName', title: '角色名称', align: 'center'}
            , {
                field: 'permName',
                templet: '<div>{{d.permissionList[0].permName}}</div>',
                title: '权限名称',
                align: 'center'
            }
            , {
                field: 'permission',
                templet: '<div>{{d.permissionList[0].permission}}</div>',
                title: '权限',
                align: 'center'
            }
            , {field: 'dName', templet: '<div>{{d.departmentList[0].dName}}</div>', title: '部门', align: 'center'}
            , {
                field: 'isDel', title: '状态', width: 100, align: 'center', templet: function (data) {
                    if (data.isDel == 0) {
                        return '<div> <input type="checkbox" checked="" name="codeSwitch" lay-skin="switch" id="open" lay-filter="switchTest" switchId=' + data.rolId + '' +
                            ' lay-text="已启用|已禁用"  value=' + data.isDel + '></div>';
                    }
                    return '<div> <input type="checkbox" lay-skin="switch" name="codeSwitch"  switchId=' + data.rolId + ' lay-filter="switchTest"' +
                        'lay-text="已启用|已禁用" value=' + data.isDel + '></div>';

                }
            }
            , {field: 'createTime', title: '创建时间', align: 'center'}
            , {field: 'updateTime', title: '修改时间', align: 'center'}
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
            layer.confirm('确定删除吗', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/rol/rolDelete",
                    data: {
                        rolId: data.rolId
                    },
                    success: function (data) {
                        if (data.code == 200) {
                            obj.del();
                            layer.close(index);
                            layer.msg("删除成功", {icon: 6, time: 3000});
                        } else {
                            layer.msg("删除失败", {icon: 5});
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
                url: '/rol/delete',
                data: {
                    isDel: 1,
                    rolId: data.elem.getAttribute("switchId")
                },
                type: 'PUT', //HTTP请求类型
                success: function (data) {
                    $.message({
                        message: "操作成功",
                        type: 'success',
                        showClose: true
                    });

                }, error: function () {
                    $.message({
                        message: "系统匆忙，请销后再试 !",
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
                url: '/rol/delete',
                data: {
                    isDel: 0,
                    rolId: data.elem.getAttribute("switchId")
                },
                type: 'PUT',
                success: function (data) {
                    $.message({
                        message: "操作成功",
                        type: 'success',
                        showClose: true
                    });

                }, error: function () {
                    $.message({
                        message: "系统匆忙，请销后再试 !",
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
        const index = layui.layer.open({
            title: "编辑",
            type: 2,
            content: "rol-edit.html",//弹出层页面
            area: ['100%', '100%'],
            success: function (layer, index) {
                const body = layui.layer.getChildFrame('body', index);
                if (data) {
                    // 取到弹出层里的元素，并把编辑的内容放进去 数据回显
                    body.find(".rolId").val(data.rolId);
                    body.find(".rName").val(data.rName);

                    // 给select标签赋值value。
                    body.find("#permName").val(data.permId);

                    // 根据id选择那一项
                    body.find("#permName option").eq(data.permId).attr("selected");

                    // 记得重新渲染表单
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        //layui.layer.full(index);//设置弹出层布满窗口
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            //layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    function addEmployee(data) {
        const index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "./rol-add.html",//弹出层页面
            area: ['600px', '300px'],
            success: function (layer, index) {
                const body = layui.layer.getChildFrame('body', index);
                if (data) {
                    // 取到弹出层里的元素，并把编辑的内容放进去
                    // 给select标签赋值value。
                    body.find("#classesName").val(data.classesId);
                    // 根据id选择那一项
                    // 记得重新渲染表单
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });

        //layui.layer.full(index);//设置弹出层布满窗口
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }


});


$(function () {

    /***********************模糊查询开始*****************************/
    $('.demoTable .searchSubmit').on('click', function () {

        if ($("#nameSearch").val() == '') {
            $.message({
                message: "输入框不能为空",
                type: 'warning',
                showClose: true
            });
            return false;
        }

        var layerIndex = layer.load(3);

        layui.use('table', function () {
            var table = layui.table;

            table.render({
                elem: '#valueTable'  //要和table标签ID一致
                , url: '/rol/rolSearchName'
                , type: "GET"
                , id: 'reload'
                , toolbar: '#toolbar'
                , defaultToolbar: ['filter', 'exports', '', {  //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                    title: '提示'
                    , layEvent: 'LAYTABLE_TIPS'
                    , icon: 'layui-icon-tips'
                }]
                , where: {
                    nameSearch: $("#nameSearch").val()
                }

                , title: '文章数据表'
                , page: true
                /**
                 * 打印数据到控制台
                 **/
                , parseData: function (res) {
                    console.log(res)
                }
                , page: true
                , limit: 10
                , loading: true
                , cols: [
                    [
                        //表头
                        {type: 'checkbox', fixed: 'left'}
                        , {type: 'numbers', title: '序号', align: 'center'}
                        , {field: 'rName', title: '角色名', align: 'center'}
                        , {
                        field: 'permName',
                        templet: '<div>{{d.permissionList[0].permName}}</div>',
                        title: '权限名称',
                        align: 'center'
                    }
                        , {
                        field: 'permission',
                        templet: '<div>{{d.permissionList[0].permission}}</div>',
                        title: '权限',
                        align: 'center'
                    }
                        , {
                        field: 'dName',
                        templet: '<div>{{d.departmentList[0].dName}}</div>',
                        title: '部门',
                        align: 'center'
                    }
                        , {
                        field: 'isDel', title: '状态', width: 100, align: 'center', templet: function (data) {
                            if (data.isDel == 0) {
                                return '<div> <input type="checkbox" checked="" name="codeSwitch" lay-skin="switch" id="open" lay-filter="switchTest" switchId=' + data.rolId + '' +
                                    ' lay-text="已启用|已禁用"  value=' + data.isDel + '></div>';
                            }
                            return '<div> <input type="checkbox" lay-skin="switch" name="codeSwitch"  switchId=' + data.rolId + ' lay-filter="switchTest"' +
                                'lay-text="已启用|已禁用" value=' + data.isDel + '></div>';

                        }
                    }
                        , {field: 'createTime', title: '创建时间', align: 'center'}
                        , {field: 'updateTime', title: '修改时间', align: 'center'}
                        , {
                        fixed: 'right',
                        width: 100,
                        align: 'center',
                        toolbar: '#barDemo',
                        title: '操作',
                        align: 'center'
                    }
                    ]
                ]
                , page: true
                , id: "delId"

            });


        });
        layer.close(layerIndex);


    });

    /***********************模糊查询结束*****************************/


})
