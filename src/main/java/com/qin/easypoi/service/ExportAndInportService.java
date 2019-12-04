package com.qin.easypoi.service;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface ExportAndInportService {
    /*
     * 导出复杂对象
     * */
    ResponseEntity<Resource> exportHorizonComplex(String fileName) throws IOException;

    /*
    *
    * 导入复杂对象
    * */
    List<Map> inportComplex(MultipartFile excel)throws Exception;

}
