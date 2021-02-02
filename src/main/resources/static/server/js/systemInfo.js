var $;
layui.config({
    base: "js/"
}).use(['layer', 'jquery'], function () {
    var layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    var layerIndex = layer.load(3, {time: 3 * 1000});
    $(".childrenBody").hide();
    $.ajax({
        type: "get",
        url: "/system/resourceUsage",
        datatype: "json",
        success: function (data) {
            //  console.log(data)
            if (data.code == 200) {
                $('#cpuNum').text(data.cpu.cpuNum + '个');
                $("#cpuUse").text(data.cpuUse + '%')
                $("#cpuFree").text(data.cpu.free + '%')
                $('#memTotal').text(data.mem.total + 'GB');
                $("#jvmTotal").text(data.jvm.total + 'MB')
                $("#memUsed").text(data.mem.used + 'GB')
                $('#jvmUsed').text(data.jvm.used + 'MB');
                $("#memFree").text(data.mem.free + 'GB')
                $("#jvmFree").text(data.jvm.free + 'MB')
                $("#memUsage").text(data.mem.usage + '%')
                $("#jvmUsage").text(data.jvm.usage + '%')
                $("#sysComputerName").text(data.sys.computerName)
                $("#sysOsName").text(data.sys.osName)
                $("#sysComputerIp").text(data.sys.computerIp)
                $("#sysOsArch").text(data.sys.osArch)
                $("#jvmName").text(data.jvm.name)
                $("#jvmVersion").text(data.jvm.version)
                $("#jvmStartTime").text(data.jvm.startTime)
                $("#jvmRunTime").text(data.jvm.runTime)
                $("#jvmHome").text(data.jvm.home)
                $("#sysUserDir").text(data.sys.userDir)
                $.each(data.sysFiles, function (index, item) {
                    $('#sysFiles').append('<tr>' +
                        '<td>' + item.dirName + '</td>' +
                        '<td>' + item.sysTypeName + '</td>' +
                        '<td>' + item.typeName + '</td>' +
                        '<td>' + item.total + '</td>' +
                        '<td>' + item.free + '</td>' +
                        '<td>' + item.used + '</td>' +
                        '<td class='+ (item.usage > 80 ? "text-danger":'')+'>' + item.usage + "%</td>" +
                        '</tr>'
                    );


                });
            }
        }, complete: function (XHR, TS) {
            $(".childrenBody").show();
            layer.close(layerIndex);
        }
    });
})