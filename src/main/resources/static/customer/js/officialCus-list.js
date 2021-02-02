layui.config({
    base: "js/"
}).use(['table', 'layer', 'form', 'jquery'], function () {
    var table = layui.table,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery
    var layerIndex = layer.load(3);

    table.render({
        elem: '#valueTable'  //要和table标签ID一致
        , url: '/cus/offCustomerList'
        , id: 'testReload'
        , toolbar: '#toolbar'
        , page: true
        , limit: 10
        , loading: true
        , cols: [[

            //表头
            {type: 'checkbox', fixed: 'left'}
            , {field: 'customerId', title: 'ID', align: 'center'}
            , {field: 'customerName', title: '姓名', align: 'center'}
            , {field: 'sex', title: '性别', align: 'center'}
            , {field: 'phone', title: '手机号码', align: 'center'}
            , {field: 'company', title: '公司', align: 'center'}
            , {field: 'address', title: '公司地址', align: 'center',}
            , {field: 'empName', templet: '<div>{{d.employeeList[0].empName}}</div>', title: '负责人', align: 'center'}
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
                    customerId = [];

                for (var i in data) {
                    customerId.push(data[i].customerId)
                }

                layer.confirm('确定要删除' + data.length + '条数据吗?', {title: '系统信息'}, function (index) {

                    $.ajax({
                        url: '/cus/delBatch',
                        type: 'PUT',
                        data: {
                            isDel: 1,
                            customerId: customerId
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
        var layerIndex = layer.load(3);

        var data = obj.data;
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm('确定删除该条客户信息吗？', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/cus/cusDeleteOff",
                    data: {
                        customerId: data.customerId
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
        content: "cus-edit.html",//弹出层页面
        area: ['100%', '100%'],
        success: function (layer, index) {
            var body = layui.layer.getChildFrame('body', index);
            if (data) {
                // 取到弹出层里的元素，并把编辑的内容放进去 数据回显
                body.find(".customerId").val(data.customerId);
                body.find(".customerName").val(data.customerName);
                body.find(".sex").val(data.sex);
                body.find(".phone").val(data.phone);
                body.find(".company").val(data.company);
                body.find(".address").val(data.address);

                // 给select标签赋值value。
                body.find("#ename").val(data.empId);

                // 根据id选择那一项
                body.find("#ename option").eq(data.empId).attr("selected");

                // 记得重新渲染表单
                form.render();
            }
            setTimeout(function () {
                layui.layer.tips('点击此处返回客户列表', '.layui-layer-setwin .layui-layer-close', {
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
        content: "./cus-add.html",//弹出层页面
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
                layui.layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
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
                , url: '/cus/cusSearchNameOfficial'
                , type: "GET"
                , id: 'testReload'
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
                        , {field: 'customerId', title: 'ID', align: 'center'}
                        , {field: 'customerName', title: '姓名', align: 'center'}
                        , {field: 'sex', title: '性别', align: 'center'}
                        , {field: 'phone', title: '手机号码', align: 'center'}
                        , {field: 'company', title: '公司', align: 'center'}
                        , {field: 'address', title: '公司地址', align: 'center',}
                        , {
                        field: 'empName',
                        templet: '<div>{{d.employeeList[0].empName}}</div>',
                        title: '负责人',
                        align: 'center'
                    }
                        , {field: 'createTime', title: '创建时间', align: 'center'}
                        , {field: 'updateTime', title: '修改时间', align: 'center'}
                        , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 150, align: 'center'}

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
