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

    // 很重要,初始化
    table = layui.table, form = layui.form;
    table.render({
        elem: '#valueTable'  //要和table标签ID一致
        , url: '/emp/adminList'
        , id: 'testReload'
        , toolbar: '#toolbar'
        , page: true
        , limit: 10
        , loading: true
        , cols: [[

            //表头
            {field: 'employeeId', title: 'ID', align: 'center'}
            , {field: 'empName', title: '登录名', sort: true, align: 'center'}
            , {field: 'sex', title: '性别', align: 'center'}
            , {field: 'age', title: '年龄', align: 'center'}
            , {field: 'phone', title: '手机', align: 'center'}
            , {field: 'address', title: '地址', align: 'center'}
            , {field: 'rname', templet: '<div>{{d.roleList[0].rname}}</div>', title: '角色', align: 'center',}
            , {field: 'createTime', title: '创建时间', align: 'center'}
            , {field: 'updateTime', title: '修改时间', align: 'center'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', align: 'center'}

        ]]
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


});


//编辑操作
function editEmployee(data) {
    var index = layui.layer.open({
        title: "编辑",
        type: 2,
        content: "/emp/emp-edit.html",//弹出层页面
        area: ['100%', '100%'],
        success: function (layero, index) {
            var body = layui.layer.getChildFrame('body', index);
            if (data) {
                // 取到弹出层里的元素，并把编辑的内容放进去 数据回显
                body.find(".employeeId").val(data.employeeId)
                body.find(".empName").val(data.empName);
                body.find(".sex").val(data.sex);
                body.find(".age").val(data.age);
                body.find(".phone").val(data.phone);
                body.find(".address").val(data.address);

                // 给select标签赋值value。
                body.find("#rname").val(data.roleId);

                // 根据id选择那一项
                body.find("#rname option").eq(data.roleId).attr("selected");

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
        //layui.layer.full(window.sessionStorage.getItem("index"));
    })
}

//添加
function addEmployee(data) {
    var index = layui.layer.open({
        title: "添加员工",
        type: 2,
        content: "./emp-add.html",//弹出层页面
        area: ['450px', '500px'],
        success: function (layero, index) {
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
