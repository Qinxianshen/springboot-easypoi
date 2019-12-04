package com.qin.easypoi.service.Impl;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.qin.easypoi.domain.DepenceModel;
import com.qin.easypoi.domain.Detail;
import com.qin.easypoi.domain.QinComplexObject;
import com.qin.easypoi.domain.StoageDatabase;
import com.qin.easypoi.service.ExportAndInportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExportAndInportServiceImpl implements ExportAndInportService {

    @Override
    public ResponseEntity<Resource> exportHorizonComplex(String fileName) throws IOException {
        TemplateExportParams params = new TemplateExportParams(
                "complexTemplate7.xlsx");

        QinComplexObject qinComplexObject = data1();
        List<Map<String,Object>> maplist = data2();
        List<Map<String,Object>> details = data3();
        StoageDatabase stoageDatabase = data4();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("modelCnName", qinComplexObject.getModelCnName());
        map.put("modelEnName",qinComplexObject.getModelEnName());
        map.put("modelNumber",qinComplexObject.getModelNumber());
        map.put("modelStuff",qinComplexObject.getModelStuff());
        map.put("devStuff",qinComplexObject.getDevStuff());
        map.put("modelState",qinComplexObject.getModelState());
        map.put("mintime",qinComplexObject.getMintime());
        map.put("minspace",qinComplexObject.getMinspace());
        map.put("requiretime", qinComplexObject.getRequiretime());
        map.put("onlyexpression",qinComplexObject.getOnlyexpression());
        map.put("mainIndex",qinComplexObject.getMainIndex());
        map.put("modelLevel",qinComplexObject.getModelLevel());
        map.put("maplist",maplist);
        map.put("detaillist",details);
        map.put("database",stoageDatabase.getDatabase());
        map.put("databaseName",stoageDatabase.getDatabaseName());
        map.put("storageCycle",stoageDatabase.getStorageCycle());

        fileName = fileName+".xlsx";

        XSSFWorkbook workbook = (XSSFWorkbook) ExcelExportUtil.exportExcel(params,map);
        FileOutputStream fos = new FileOutputStream(fileName);
        workbook.write(fos);
        fos.close();

        //下载文件
        File dbfFile = new File(fileName);
        if (!dbfFile.exists() || dbfFile.isDirectory()) {
            dbfFile.createNewFile();
        }
        InputStreamResource resource = new InputStreamResource(new FileInputStream(dbfFile));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-disposition", "attachment;filename="+ fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(dbfFile.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    private QinComplexObject data1(){
        QinComplexObject complexObject = new QinComplexObject();
        complexObject.setModelCnName("模型一");
        complexObject.setModelEnName("model No.1");
        complexObject.setModelNumber("HB1235879530");
        complexObject.setModelStuff("小明");
        complexObject.setDevStuff("小芳");
        complexObject.setModelState("开发中");
        complexObject.setMinspace("100MB");
        complexObject.setMintime("2 days");
        complexObject.setRequiretime("4 days");
        complexObject.setOnlyexpression("说明一：这是一个说明");
        complexObject.setMainIndex("A分区 索引一");
        complexObject.setModelState("A");
        complexObject.setModelLevel("A");
        return complexObject;
    }
    private List<Map<String,Object>> data2(){
        //相应字段要与模板对应
        List<Map<String,Object>> maplist = new ArrayList<Map<String, Object>>();
        Map<String,Object> map1 = new HashMap<String, Object>();
        Map<String,Object> map2 = new HashMap<String, Object>();
        map1.put("depencemodelname","Model 1");
        map1.put("relateDescription","这是一个相关描述一");
        map2.put("depencemodelname","Model 2");
        map2.put("relateDescription","这是一个相关描述二");
        maplist.add(map1);
        maplist.add(map2);

        return maplist;
    }
    private List<Map<String,Object>> data3(){

        List<Map<String,Object>> maplist = new ArrayList<Map<String, Object>>();
        Map<String,Object> map1 = new HashMap<String, Object>();
        Map<String,Object> map2 = new HashMap<String, Object>();
        map1.put("id","1");
        map1.put("keys","主键一");
        map1.put("cnMark","标识一");
        map1.put("enMark","mark one");
        map1.put("type","NUMBER");
        map1.put("length","1024");
        map1.put("unit","cm");
        map1.put("defaut","1024");
        map1.put("attributesExpression","属性一");
        map1.put("datasource","数据源一");
        map1.put("datasourceText","数据源字段一");
        map1.put("algorithmExpression","算法描述一");
        map1.put("function","函数一");

        map2.put("id","2");
        map2.put("keys","主键二");
        map2.put("cnMark","标识二");
        map2.put("enMark","mark two");
        map2.put("type","NUMBER");
        map2.put("length","1024");
        map2.put("unit","cm");
        map2.put("defaut","1024");
        map2.put("attributesExpression","属性二");
        map2.put("datasource","数据源二");
        map2.put("datasourceText","数据源字段二");
        map2.put("algorithmExpression","算法描述二");
        map2.put("function","函数二");
        maplist.add(map1);
        maplist.add(map2);


        return maplist;
    }
    private StoageDatabase data4(){
        StoageDatabase stoageDatabase = new StoageDatabase();
        stoageDatabase.setDatabase("MySql");
        stoageDatabase.setDatabaseName("名字一");
        stoageDatabase.setStorageCycle("两天");
        return stoageDatabase;
    }
}
