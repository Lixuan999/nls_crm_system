layui.config({
    base: "js/"
}).use(['table', 'layer', 'form', 'jquery'], function () {
    var table = layui.table,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    var layerIndex = layer.load(3);

    table.render({
        skin: 'line'
        , elem: '#valueTable'  //要和table标签ID一致
        , url: '/gam/gambit'
        , id: 'reload'
        , toolbar: '#toolbar'
        , cols: [[

            //表头
            {type: 'checkbox', fixed: 'left'}
            , {type: 'numbers', title: '序号', align: 'center'}
            , {field: 'gheadline', title: '标题', align: 'center', edit: 'text'}
            , {field: 'gauditor', title: '审核人', align: 'center', edit: 'text'}
            , {field: 'gaudiTime', title: '审核时间', align: 'center', edit: 'text'}
            , {field: 'gfounder', title: '创建人', align: 'center', edit: 'text'}
            , {field: 'gcheckState', title: '审核状态', align: 'center', edit: 'text'}
            , {field: 'createTime', title: '创建时间', align: 'center', edit: 'text'}
            , {field: 'updateTime', title: '修改时间', align: 'center', edit: 'text'}
            , {fixed: 'right', align: 'center', toolbar: '#barDemo', title: '操作', align: 'center'}
        ]]
        , limit: 10
        , limits: [10, 20, 25, 50, 100]
        , parseData: function (data) {
            //打印数据
            console.log(data)
        }


        /**
         * 开启分页34
         */
        , page: true
        , where: {isDel: 0}
        , id: 'gambitListReload'

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
                var checkStatusId = table.checkStatus('gambitListReload'),
                    data = checkStatusId.data,
                    gambitId = [];

                for (var i in data) {
                    gambitId.push(data[i].gambitId)
                }

                layer.confirm('确定要删除' + data.length + '条数据么?', {title: '系统信息'}, function (index) {
                    var layerIndex = layer.load(3);
                    $.ajax({
                        url: '/gam/delBatchGambit',
                        type: 'PUT',
                        data: {
                            isDel: 1,
                            gambitId: gambitId
                        },
                        success: function (res) {
                            if (res.code == 200) {
                                table.reload('gambitListReload', {});
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
            layer.confirm('确定删除该条文章信息吗？', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/gam/gamDelete",
                    data: {
                        gambitId: data.gambitId
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
     * 监听单元格编辑
     */
    table.on('edit(employeeManage)', function (obj) {
        var value = obj.value //得到修改后的值
            , data = obj.data //得到所在行所有键值
            , field = obj.field; //得到字段

        const jiuData = $(this).prev().text();

        if ((field == 'gheadline')) {
            if (value == '') {
                $.message({
                    message: '标题不能为空',
                    type: 'warning',
                    showClose: true
                });
                $(this).val(jiuData)
                return false;
            }
        }

        //编辑
        var layerIndex = layer.load(3);
        $.ajax({
            type: "PUT",
            url: "/gam/gamUpdate",
            datatype: "json",
            data: data,
            success: function (data) {

                if (data.code == 200) {
                    $.message({
                        message: '操作成功',
                        type: 'success',
                        showClose: true
                    });
                } else {
                    $.message({
                        message: data.msg,
                        type: 'warning',
                        showClose: true
                    });
                }
            }, error: function () {
                $.message({
                    message: '请求异常 !',
                    type: 'error',
                    showClose: true
                });
            }, complete: function (XHR, TS) {
                layer.close(layerIndex);
            }

        });
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
                url: '/gam/delete',
                data: {
                    isDel: 1,
                    gambitId: data.elem.getAttribute("switchId")
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
                url: '/gam/delete',
                data: {
                    isDel: 0,
                    gambitId: data.elem.getAttribute("switchId")
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
        var layerIndex = layer.load(3);

        var index = layui.layer.open({
            title: "编辑",
            type: 2,
            content: "gam-edit.html",//弹出层页面
            area: ['100%', '100%'],
            success: function (layer, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (data) {
                    // 取到弹出层里的元素，并把编辑的内容放进去 数据回显
                    body.find(".gambitId").val(data.gambitId)
                    body.find(".gHeadline").val(data.gheadline);
                    body.find(".gAuditor").val(data.gauditor);
                    body.find(".gAudiTime").val(data.gaudiTime);
                    body.find(".gFounder").val(data.gfounder);
                    body.find(".gCheckState").val(data.gcheckstate);

                    // 给select标签赋值value。
                    body.find("#rName").val(data.roleId);

                    // 根据id选择那一项
                    body.find("#rName option").eq(data.roleId).attr("selected");

                    // 记得重新渲染表单
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回话题列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layer.close(layerIndex);

        //layui.layer.full(index);//设置弹出层布满窗口
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            //layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }


    //添加
    function addEmployee(data) {
        var layerIndex = layer.load(3);

        var index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "./gam-add.html",//弹出层页面
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
                    layui.layer.tips('点击此处返回话题列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layer.close(layerIndex);

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
                , url: '/gam/gamSearchName'
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
                        , {field: 'gambitId', title: 'ID', align: 'center'}
                        , {field: 'gheadline', title: '标题', align: 'center'}
                        , {field: 'gauditor', title: '审核人', align: 'center'}
                        , {field: 'gaudiTime', title: '审核时间', align: 'center'}
                        , {field: 'gfounder', title: '创建人', align: 'center'}
                        , {
                        field: 'isDel', title: '状态', width: 100, align: 'center', templet: function (data) {
                            if (data.isDel == 0) {
                                return '<div> <input type="checkbox" checked="" name="codeSwitch" lay-skin="switch" id="open" lay-filter="switchTest" switchId=' + data.gambitId + '' +
                                    ' lay-text="已启用|已禁用"  value=' + data.isDel + '></div>';
                            }
                            return '<div> <input type="checkbox" lay-skin="switch" name="codeSwitch"  switchId=' + data.gambitId + ' lay-filter="switchTest"' +
                                'lay-text="已启用|已禁用" value=' + data.isDel + '></div>';

                        }
                    }
                        , {field: 'gcheckState', title: '审核状态', align: 'center'}
                        , {field: 'createTime', title: '创建时间', align: 'center'}
                        , {field: 'updateTime', title: '修改时间', align: 'center'}
                        , {fixed: 'right', align: 'center', toolbar: '#barDemo', title: '操作', align: 'center'}

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