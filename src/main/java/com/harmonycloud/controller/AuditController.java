package com.harmonycloud.controller;

import com.harmonycloud.entity.CimsAudit;
import com.harmonycloud.result.CimsResponseWrapper;
import com.harmonycloud.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qidong
 * @date 2019/3/14
 */
@RestController
public class AuditController {

    @Autowired
    AuditService auditService;

    @PostMapping("/auditList")
    public CimsResponseWrapper<List> getAuditList() {
        List<CimsAudit> cimsAuditList = auditService.getAuditList();
        return new CimsResponseWrapper<List>(true, null, cimsAuditList);
    }
}
