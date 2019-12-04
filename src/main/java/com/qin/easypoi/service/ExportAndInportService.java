package com.qin.easypoi.service;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ExportAndInportService {
    /*
     * 导出复杂对象
     * */
    ResponseEntity<Resource> exportHorizonComplex(String fileName) throws IOException;
}
