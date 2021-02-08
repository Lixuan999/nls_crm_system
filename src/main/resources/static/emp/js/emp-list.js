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
        , url: '/emp/employee'
        , id: 'reload'
        , toolbar: '#toolbar'
        , page: true
        , limit: 10
        , cols: [[

            //表头
            {type: 'checkbox', fixed: 'left'}
            , {type: 'numbers', title: '序号', align: 'center', sort: true}
            , {field: 'accountName', title: '员工昵称', align: 'center'}
            , {field: 'empName', title: '员工姓名', align: 'center'}
            , {field: 'sex', title: '性别', align: 'center', sort: true}
            , {field: 'age', title: '年龄', align: 'center'}
            , {field: 'phone', title: '手机', align: 'center'}
            , {field: 'address', title: '居住地址', align: 'center'}
            , {
                field: 'isDel', title: '状态', width: 100, align: 'center', templet: function (data) {
                    if (data.isDel == 0) {
                        return '<div> <input type="checkbox" checked="" name="codeSwitch" lay-skin="switch" id="open" lay-filter="switchTest" switchId=' + data.employeeId + '' +
                            ' lay-text="已启用|已禁用"  value=' + data.isDel + '></div>';
                    }
                    return '<div> <input type="checkbox" lay-skin="switch" name="codeSwitch"  switchId=' + data.employeeId + ' lay-filter="switchTest"' +
                        'lay-text="已启用|已禁用" value=' + data.isDel + '></div>';

                }
            }
            , {
                field: 'rName',
                templet: '<div>{{d.roleList[0].rName}}</div>',
                title: '角色',
                align: 'center'
            }
            , {
                field: 'dName',
                templet: '<div>{{d.roleList[0].departmentList[0].dName}}</div>',
                title: '部门',
                align: 'center'
            }
            , {fixed: 'right', width: 100, align: 'center', toolbar: '#barDemo', title: '操作', align: 'center'}
            , {field: 'rolId', templet: '<div>{{d.roleList[0].rolId}}</div>', hide: true}
            , {
                field: 'departmentId',
                templet: '<div>{{d.roleList[0].departmentList[0].departmentId}}</div>',
                hide: true
            }

        ]]
        , limit: 10
        , limits: [10, 20, 25, 50, 100]
        , parseData: function (data) {
            layer.close(layerIndex);
            //打印数据
            console.log(data)
        }
        //开启分页
        , page: true
        , where: {isDel: 0}
        , id: 'listReload'

    });

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
                    employeeId = [];

                for (var i in data) {
                    employeeId.push(data[i].employeeId)
                }

                layer.confirm('确定要删除' + data.length + '条数据吗?', {title: '系统信息'}, function (index) {
                    var layerIndex = layer.load(3);
                    $.ajax({
                        url: '/emp/delBatch',
                        type: 'PUT',
                        data: {
                            isDel: 1,
                            employeeId: employeeId
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
            layer.confirm('确定删除吗', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/emp/empDelete",
                    data: {
                        employeeId: data.employeeId
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
                url: '/emp/delete',
                data: {
                    isDel: 1,
                    employeeId: data.elem.getAttribute("switchId")
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
        } else {
            /**
             * 启动标签
             * 状态 赋值为 0
             */
            $.ajax({
                url: '/emp/delete',
                data: {
                    isDel: 0,
                    employeeId: data.elem.getAttribute("switchId")
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

    //编辑操作
    function editEmployee(data) {
        const index = layui.layer.open({
            title: "员工信息修改",
            type: 2,
            content: "emp-edit.html?employeeId=" + data.employeeId,
            area: ['100%', '100%'],
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回员工列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 1000)
            }
        });
        layui.layer.full(index);
    }

    //添加
    function addEmployee(data) {
        var index = layui.layer.open({
            title: "添加员工",
            type: 2,
            content: "./emp-add.html",//弹出层页面
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
                    layui.layer.tips('点击此处返回员工列表', '.layui-layer-setwin .layui-layer-close', {
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

    layer.close(layerIndex);


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
                , url: '/emp/empSearchName'
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

                , title: '员工数据表'
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
                        , {field: 'employeeId', title: 'ID', align: 'center'}
                        , {field: 'accountName', title: '员工昵称', align: 'center'}
                        , {field: 'empName', title: '员工姓名', align: 'center'}
                        , {field: 'sex', title: '性别', align: 'center'}
                        , {field: 'age', title: '年龄', align: 'center'}
                        , {field: 'phone', title: '手机', align: 'center'}
                        , {field: 'address', title: '居住地址', align: 'center'}
                        , {
                        field: 'isDel', title: '状态', width: 100, align: 'center', templet: function (data) {
                            if (data.isDel == 0) {
                                return '<div> <input type="checkbox" checked="" name="codeSwitch" lay-skin="switch" id="open" lay-filter="switchTest" switchId=' + data.employeeId + '' +
                                    ' lay-text="已启用|已禁用"  value=' + data.isDel + '></div>';
                            }
                            return '<div> <input type="checkbox" lay-skin="switch" name="codeSwitch"  switchId=' + data.employeeId + ' lay-filter="switchTest"' +
                                'lay-text="已启用|已禁用" value=' + data.isDel + '></div>';

                        }
                    }
                        , {
                        field: 'rName',
                        templet: '<div>{{d.roleList[0].rName}}</div>',
                        title: '角色',
                        align: 'center'
                    }
                        , {
                        field: 'dName',
                        templet: '<div>{{d.roleList[0].departmentList[0].dName}}</div>',
                        title: '部门',
                        align: 'center'
                    }
                        , {
                        fixed: 'right',
                        width: 100,
                        align: 'center',
                        toolbar: '#barDemo',
                        title: '操作',
                        align: 'center'
                    }
                        , {field: 'rolId', templet: '<div>{{d.roleList[0].rolId}}</div>', hide: true}
                        , {
                        field: 'departmentId',
                        templet: '<div>{{d.roleList[0].departmentList[0].departmentId}}</div>',
                        hide: true
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
