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
        , url: '/con/consult'
        , id: 'testReload'
        , toolbar: '#toolbar'
        , limit: 10
        , loading: false
        , cols: [
            [

            {type: 'checkbox', fixed: 'left'}
            , {field: 'consultId', title: 'ID', align: 'center'}
            , {field: 'cconsultContent', title: '咨询内容', align: 'center'}
            , {field: 'cname', title: '姓名', align: 'center'}
            , {field: 'csex', title: '性别', align: 'center'}
            , {field: 'cphone', title: '电话', align: 'center'}
            , {field: 'createTime', title: '创建时间', align: 'center'}
            , {field: 'updateTime', title: '最后一次修改时间', align: 'center'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', align: 'center'}

           ]
        ]
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
                    consultId = [];

                for (var i in data) {
                    consultId.push(data[i].consultId)
                }

                layer.confirm('确定要删除' + data.length + '条数据吗?', {title: '系统信息'}, function (index) {
                    var layerIndex = layer.load(3);

                    $.ajax({
                        url: '/con/delBatch',
                        type: 'PUT',
                        data: {
                            isDel: 1,
                            consultId: consultId
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
            layer.msg('ID：' + data.consultId + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm('确定删除该条咨询信息吗？', function (index) {
                console.log(data);
                $.ajax({
                    type: "DELETE",
                    url: "/con/conDelete",
                    data: {
                        consultId: data.consultId
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


});


//编辑操作
function editEmployee(data) {
    var layerIndex = layer.load(3);

    var index = layui.layer.open({
        title: "编辑",
        type: 2,
        content: "con-edit.html",//弹出层页面
        area: ['450px', '500px'],
        success: function (layero, index) {
            var body = layui.layer.getChildFrame('body', index);
            if (data) {
                // 取到弹出层里的元素，并把编辑的内容放进去 数据回显
                body.find(".cId").val(data.cid)
                body.find(".cConsultContent").val(data.cconsultContent);
                body.find(".cName").val(data.cname);
                body.find(".cSex").val(data.csex);
                body.find(".cPhone").val(data.cphone);

                // 记得重新渲染表单
                form.render();
            }
            setTimeout(function () {
                layui.layer.tips('点击此处返回咨询列表', '.layui-layer-setwin .layui-layer-close', {
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


$(function () {

    /***********************模糊查询开始*****************************/
    $('.demoTable .searchSubmit').on('click', function () {

        if ($("#nameSearch").val() == '') {
            $.message({
                message: "输入框不能为空 !",
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
                , url: '/con/conSearchName'
                , type: "GET"
                , id: 'testReload'
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
                        , {
                        field: 'cid',
                        title: '编号',
                        width: 110,
                        fixed: 'left',
                        unresize: true,
                        sort: true,
                        align: 'center'
                    }
                        , {field: 'cconsultContent', title: '咨询内容', sort: true, align: 'center'}
                        , {field: 'cname', title: '姓名', sort: true, align: 'center'}
                        , {field: 'csex', title: '性别', sort: true, align: 'center'}
                        , {field: 'cphone', title: '电话', sort: true, align: 'center'}
                        , {field: 'createTime', title: '创建时间', sort: true, align: 'center'}
                        , {field: 'updateTime', title: '最后一次修改时间', sort: true, align: 'center'}
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
