package com.harmonycloud.controller;

import com.harmonycloud.entity.CimsAudit;
import com.harmonycloud.result.CimsResponseWrapper;
import com.harmonycloud.service.AuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @ApiOperation(value = "get all audit list", httpMethod = "GET")
    @GetMapping("/auditList")
    public CimsResponseWrapper<List> getAuditList() throws Exception{
        List<CimsAudit> cimsAuditList = auditService.getAuditList();
        return new CimsResponseWrapper<List>(true, null, cimsAuditList);
    }

    @ApiOperation(value = "get audit list by user id", httpMethod = "GET")
    @GetMapping("/auditListById")
    public CimsResponseWrapper<List> getAuditListByUserId(@RequestParam("userId") Integer userId) throws Exception {
        List<CimsAudit> cimsAuditList = auditService.getAuditListByUserId(userId);
        return new CimsResponseWrapper<List>(true, null, cimsAuditList);
    }
}
