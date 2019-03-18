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
 * @author qidong
 * @date 2019/3/14
 */
@Service
public class AuditService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuditRepository auditRepository;

    public List<CimsAudit> getAuditList() throws Exception {
        List<CimsAudit> cimsAuditList = null;
        try {
            cimsAuditList = auditRepository.findAll();
        } catch(Exception e) {
            logger.info(e.getMessage());
            throw new AuditException(ErrorMsgEnum.QUERY_DATA_ERROR.getMessage());
        }
        return cimsAuditList;
    }

    public List<CimsAudit> getAuditListByUserId(Integer userId) throws Exception {
        List<CimsAudit> cimsAuditList = null;
        try {
            cimsAuditList = auditRepository.findByUserId(userId);
        } catch(Exception e) {
            logger.info(e.getMessage());
            throw new AuditException(ErrorMsgEnum.QUERY_DATA_ERROR.getMessage());
        }
        return cimsAuditList;
    }
}
