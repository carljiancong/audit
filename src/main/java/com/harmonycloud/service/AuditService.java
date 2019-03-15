package com.harmonycloud.service;

import com.harmonycloud.entity.CimsAudit;
import com.harmonycloud.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author qidong
 * @date 2019/3/14
 */
public class AuditService {

    @Autowired
    AuditRepository auditRepository;

    public List<CimsAudit> getAuditList() {
        List<CimsAudit> cimsAuditList = null;
        try {
            cimsAuditList = auditRepository.findAll();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return cimsAuditList;
    }
}
