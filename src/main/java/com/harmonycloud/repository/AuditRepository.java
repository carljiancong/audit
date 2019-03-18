package com.harmonycloud.repository;

import com.harmonycloud.entity.CimsAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qidong
 * @date 2019/3/15
 */
public interface AuditRepository extends JpaRepository<CimsAudit, Integer> {
    List<CimsAudit> findByUserId(Integer userId);

}
