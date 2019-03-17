package com.harmonycloud.controller;

import com.alibaba.fastjson.JSON;
import com.harmonycloud.bo.Audit;
import com.harmonycloud.entity.CimsAudit;
import com.harmonycloud.result.CimsResponseWrapper;
import com.harmonycloud.rocketmq.Producer;
import com.harmonycloud.service.AuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;

/**
 * @author qidong
 * @date 2019/3/14
 */
@Api
@RestController
public class AuditController {

    @Autowired
    AuditService auditService;

    @Autowired
    Producer producer;

    @ApiOperation(value = "audit list", httpMethod = "GET")
    @GetMapping("/auditList")
    public CimsResponseWrapper<List> getAuditList() throws Exception{
        List<CimsAudit> cimsAuditList = auditService.getAuditList();
        return new CimsResponseWrapper<List>(true, null, cimsAuditList);
    }

//    @GetMapping("/send")
//    public String send() {
//        Audit audit = new Audit(new Date(), "Critical", "Computer", "11.11.11.11",
//                12, "CIMS", "1212121212", "MedicationOrder", "123");
//        try{
//            producer.send("OrderTopic","OrderPush", JSON.toJSONString(audit));
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
