layui.config({
    base: "js/"
}).use(['table', 'layer', 'form', 'jquery'], function () {
    let table = layui.table,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    const layerIndex = layer.load(3);


    table.render({
        elem: '#depInfo'  //要和table标签ID一致
        , url: '/dep/department' //数据接口
        , id: 'reload'
        , toolbar: '#toolbar'//绑定工具条模板
        , page: true
        , loading: true
        //打印数据到控制台
        , parseData: function (res) {
            console.log(res)
        }
        , cols: [[

            //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'departmentId', title: 'ID', align: 'center', sort: true, width: 80}
            , {field: 'dName', title: '部门名称', align: 'center'}
            , {field: 'dmanager', title: '部门领导', align: 'center'}
            , {field: 'dpopulation', title: '部门人数', align: 'center', sort: true, width: 120}
            , {
                field: 'roleList', templet: function (data) {
                    const list = [];
                    data.roleList.forEach((item) => {
                        list.push(item.rName);
                    });
                    return list.toString();
                }, title: '角色列表', align: 'center',width:320
            }
            , {field: 'createTime', title: '创建时间', align: 'center', sort: true}
            , {field: 'updateTime', title: '修改时间', align: 'center', sort: true}
            , {fixed: 'right', align: 'center', toolbar: '#barDemo', title: '操作', align: 'center'}
        ]]

        , limit: 100
        , parseData: function (data) {

            //打印数据
            console.log(data)
        }
        //开启分页
        , page: false
        , where: {isDel: 0}
        , id: 'listReload'
    });
    layer.close(layerIndex);

    $ = layui.$;
    /**
     * 批量删除
     * @type {pe|jQuery|HTMLElement}
     */
    var active = {
        /**
         * 批量删除链接
         *
         * */
        closeBtn: function () {
            const $checkbox = $('table input[type="checkbox"][name="layTableCheckbox"]');
            const $checked = $('table input[type="checkbox"][name="layTableCheckbox"]:checked');
            if ($checkbox.is(":checked")) {
                const checkStatusId = table.checkStatus('listReload'),
                    data = checkStatusId.data,
                    departmentId = [];

                for (const i in data) {
                    departmentId.push(data[i].departmentId)
                }

                layer.confirm('确定要删除' + data.length + '条数据吗?', {title: '系统信息'}, function (index) {
                    const layerIndex = layer.load(3);

                    $.ajax({
                        url: '/dep/delBatch',
                        type: 'PUT',
                        data: {
                            isDel: 1,
                            departmentId: departmentId
                        },
                        success: function (res) {
                            if (res.code == 200) {
                                table.reload('listReload', {});
                                $.message({
                                    message: res.msg,
                                    type: 'success',
                                    showClose: true
                                });


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
            layer.confirm('确定删除该条部门信息吗？', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/dep/depDelete",
                    data: {
                        departmentId: data.departmentId
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
                url: '/dep/delete',
                data: {
                    isDel: 1,
                    departmentId: data.elem.getAttribute("switchId")
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
                url: '/dep/delete',
                data: {
                    isDel: 0,
                    departmentId: data.elem.getAttribute("switchId")
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


    //编辑操作
    function editEmployee(data) {
        var index = layui.layer.open({
            title: "编辑",
            type: 2,
            content: "dep-edit.html",//弹出层页面
            area: ['100%', '100%'],
            success: function (layer, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (data) {
                    // 取到弹出层里的元素，并把编辑的内容放进去 数据回显
                    body.find(".departmentId").val(data.departmentId)
                    body.find(".dName").val(data.dName);
                    body.find(".dManager").val(data.dmanager);
                    body.find(".dPopulation").val(data.dpopulation);

                    // 给select标签赋值value。
                    body.find("#rName").val(data.roleId);
                    // 根据id选择那一项
                    body.find("#rName option").eq(data.roleId).attr("selected");

                    //记得重新渲染表单
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回部门列表', '.layui-layer-setwin .layui-layer-close', {
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

    //添加
    function addEmployee(data) {
        var index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "./dep-add.html",//弹出层页面
            area: ['450px', '500px'],
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
                    layui.layer.tips('点击此处返回部门列表', '.layui-layer-setwin .layui-layer-close', {
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
                , url: '/dep/depSearchName'
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
                // , offset: 'auto'
                // , height: 'full-105'
                , loading: true
                , cols: [
                    [
                        //表头
                        {type: 'checkbox', fixed: 'left'}
                        , {field: 'departmentId', title: 'ID', align: 'center', sort: true}
                        , {field: 'dName', title: '部门名称', align: 'center'}
                        , {field: 'dmanager', title: '部门领导', align: 'center'}
                        , {field: 'dpopulation', title: '部门人数', align: 'center'}
                        , {
                        field: 'rName',
                        templet: '<div>{{d.roleList[0].rName}}</div>',
                        title: '角色',
                        sort: true,
                        align: 'center',
                        width: 150
                    }
                        , {
                        field: 'isDel', title: '状态', width: 100, align: 'center', templet: function (data) {
                            if (data.isDel == 0) {
                                return '<div> <input type="checkbox" checked="" name="codeSwitch" lay-skin="switch" id="open" lay-filter="switchTest" switchId=' + data.departmentId + '' +
                                    ' lay-text="已启用|已禁用"  value=' + data.isDel + '></div>';
                            }
                            return '<div> <input type="checkbox" lay-skin="switch" name="codeSwitch"  switchId=' + data.departmentId + ' lay-filter="switchTest"' +
                                'lay-text="已启用|已禁用" value=' + data.isDel + '></div>';

                        }
                    }
                        , {field: 'createTime', title: '创建时间', align: 'center', sort: true}
                        , {field: 'updateTime', title: '修改时间', align: 'center', sort: true}
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
