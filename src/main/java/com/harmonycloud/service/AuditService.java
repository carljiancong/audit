package com.harmonycloud.service;

import com.harmonycloud.entity.CimsAudit;
import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.exception.AuditException;
import com.harmonycloud.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date 2019/3/14
 */
@Service
public class AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);

    @Autowired
    private AuditRepository auditRepository;

    /**
     * get all audit list
     * @return
     * @throws Exception
     */
    public List<CimsAudit> getAuditList() throws Exception {
        List<CimsAudit> cimsAuditList = auditRepository.findAll();
        return cimsAuditList;
    }

    /**
     * get audit list by user id
     * @param userId
     * @return
     * @throws Exception
     */
    public List<CimsAudit> getAuditListByUserId(Integer userId) throws Exception {
        List<CimsAudit> cimsAuditList = auditRepository.findByUserId(userId);
        return cimsAuditList;
    }
}
