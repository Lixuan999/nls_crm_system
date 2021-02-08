layui.use('laydate', function () {
    var laydate = layui.laydate;

    //执行一个laydate实例
    laydate.render({
        elem: '#start' //指定元素
    });

    //执行一个laydate实例
    laydate.render({
        elem: '#end' //指定元素
    });
});


// 方法渲染
// var customerTable;
var form;
var table;
layui.use('table', function () {

    //初始化
    table = layui.table, form = layui.form;
    table.render({
        elem: '#valueTable'  //要和table标签ID一致
        , url: '/emp/employeeDelList'
        , id: 'reload'
        , toolbar: '#toolbar'
        , page: true
        , limit: 10
        , loading: true
        , cols: [[

            //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'employeeId', title: 'ID', align: 'center', sort: true}
            , {field: 'empName', title: '登录名', align: 'center'}
            , {field: 'sex', title: '性别', align: 'center', sort: true}
            , {field: 'age', title: '年龄', align: 'center', sort: true}
            , {field: 'phone', title: '手机', align: 'center'}
            , {field: 'address', title: '地址', align: 'center'}
            , {field: 'createTime', title: '创建时间', align: 'center', sort: true}
            , {field: 'updateTime', title: '修改时间', align: 'center', sort: true}
            , {fixed: 'right',  align: 'center', toolbar: '#barDemo', title: '操作', align: 'center'}

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

                layer.confirm('确定要恢复' + data.length + '条数据吗?', {title: '系统信息'}, function (index) {
                    var layerIndex = layer.load(2);
                    $.ajax({
                        url: '/emp/recoverBatch',
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
                    message: '请选中要恢复的数据',
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


    //监听锁定操作
    form.on('checkbox(employeeManage)', function (obj) {
        layer.tips(this.value + ' ' + this.name + '：' + obj.elem.checked, obj.othis);
    });

    //监听恢复操作
    table.on('tool(employeeManage)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'resume') {
            layer.confirm('确定恢复吗', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/emp/empResume",
                    data: {
                        employeeId: data.employeeId
                    },
                    success: function (data) {
                        if (data.code == 200) {
                            obj.del();
                            layer.close(index);
                            layer.msg("恢复成功", {icon: 6, time: 3000});
                        } else {
                            layer.msg("恢复失败", {icon: 5});
                        }
                    }
                });

            });
        }
    });


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

        layui.use('table', function () {
            var table = layui.table;

            table.render({
                elem: '#valueTable'  //要和table标签ID一致
                , url: '/emp/empSearchNameDel'
                , type: "GET"
                , id: 'reload'
                , toolbar: '#toolbar'
                , defaultToolbar: ['filter', 'exports', 'print', {  //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
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
                        , {field: 'employeeId', title: 'ID', align: 'center', sort: true}
                        , {field: 'empName', title: '登录名', align: 'center'}
                        , {field: 'sex', title: '性别', align: 'center', sort: true}
                        , {field: 'age', title: '年龄', align: 'center', sort: true}
                        , {field: 'phone', title: '手机', align: 'center'}
                        , {field: 'address', title: '地址', align: 'center'}
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


    });
    /***********************模糊查询结束*****************************/


})
