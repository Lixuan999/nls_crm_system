package com.ckf.crm.controller;

import com.ckf.crm.system.SystemServer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务器监控
 *
 * @author 安详的苦丁茶
 * @date 2020/6/1923:30
 */

@RestController
@RequestMapping("/system/")
public class ServerController {
    /**
     * 服务器资源监控
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/resourceUsage")
    public Map<String, Object> server() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        SystemServer server = new SystemServer();
        server.copyTo();
        String format = new DecimalFormat("0.00").format(((100 - server.getCpu().getFree())));
        map.put("cpuUse", format);
        map.put("cpu", server.getCpu());
        map.put("mem", server.getMem());
        map.put("jvm", server.getJvm());
        map.put("sysFiles", server.getSysFiles());
        map.put("sys", server.getSys());
        map.put("code", 200);
        return map;
    }
}
