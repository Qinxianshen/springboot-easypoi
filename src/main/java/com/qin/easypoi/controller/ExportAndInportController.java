package com.qin.easypoi.controller;


import com.alibaba.fastjson.JSON;
import com.qin.easypoi.service.ExportAndInportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/exportandinport")
public class ExportAndInportController {

    @Resource
    public ExportAndInportService exportAndInportService;

    /*
     * 导出复杂对象
     * */
    @GetMapping("/exportComplex")
    public ResponseEntity<org.springframework.core.io.Resource> exportComplex(HttpServletResponse response, String fileName) throws IOException {

        try {
            return exportAndInportService.exportHorizonComplex(fileName);
        } catch (IOException e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
        return null;
    }

}
