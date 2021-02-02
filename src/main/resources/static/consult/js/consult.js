
    /*********************执行异步添加开始**************************/
    $("#zx_submit").click(function () {

        if ($("#c_consult_content").val() == '') {
            layer.msg("咨询内容不能为空！", {icon: 5})
            return false;
        }

        if ($("#c_name").val() == '') {
            layer.msg("姓名不能为空！", {icon: 5})
            return false;
        }

        if ($("#c_phone").val() == '') {
            layer.msg("电话不能为空！", {icon: 5})
            return false;
        }

        /**
         * 判断选择了那个性别
         * */
        var sex = $('input:radio[name="radio"]:checked').val();
        console.log(sex)

        $.ajax({
            url: "/con/conAdd",
            type: "POST",
            data: {
                cConsultContent: $("#c_consult_content").val(),
                cName: $("#c_name").val(),
                cSex: $('input:radio[name="radio"]:checked').val(),
                cPhone: $("#c_phone").val()

            },
            success: function (data) {
                if (data.code == 200) {
                    console.log(data)
                    layer.msg("操作成功", {icon: 6, time: 3000});

                    setTimeout(function () {
                        parent.location.reload();
                    }, 2000);
                    return false;
                }
            },
            error: function () {
                layer.msg("请求异常", {icon: 0, time: 3000});
            }
        });
    })
    /***************执行异步添加结束***************/

