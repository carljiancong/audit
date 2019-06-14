package com.harmonycloud.entity;

import com.harmonycloud.dto.Audit;

import javax.persistence.*;
import java.util.Date;

/**
 * @date 2019/3/15
 */
@Entity
@Table(name = "cims_audit")
public class CimsAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer auditId;
    @Column(name = "audit_date")
    private Date auditDate;
    @Column(name = "severity_cd")
    private String severityCD;
    @Column(name = "workstation")
    private String workstation;
    @Column(name = "client_ip")
    private String clientIp;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "project_code")
    private String projectCode;
    @Column(name = "correlation_id")
    private String correlationId;
    @Column(name = "application_name")
    private String applicationName;
    @Column(name = "infomation")
    private String infomation;

    public CimsAudit() {
    }

    public CimsAudit(Integer auditId, Date auditDate, String severityCD, String workstation,
                     String clientIp, Integer userId, String projectCode,
                     String correlationId, String applicationName, String infomation) {
        this.auditId = auditId;
        this.auditDate = auditDate;
        this.severityCD = severityCD;
        this.workstation = workstation;
        this.clientIp = clientIp;
        this.userId = userId;
        this.projectCode = projectCode;
        this.correlationId = correlationId;
        this.applicationName = applicationName;
        this.infomation = infomation;
    }
    public CimsAudit(Audit audit) {
        this.auditDate = audit.getDate();
        this.severityCD = audit.getSeverityCD();
        this.workstation = audit.getWorkstation();
        this.clientIp = audit.getClientIp();
        this.userId = audit.getUserId();
        this.projectCode = audit.getProjectCode();
        this.correlationId = audit.getCorrelationId();
        this.applicationName = audit.getApplicationName();
        this.infomation = audit.getInfomation();

    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer id) {
        this.auditId = id;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date date) {
        this.auditDate = date;
    }

    public String getSeverityCD() {
        return severityCD;
    }

    public void setSeverityCD(String severityCD) {
        this.severityCD = severityCD;
    }

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }
}
