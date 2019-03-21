package com.harmonycloud.exception;

import com.harmonycloud.enums.ErrorMsgEnum;
import com.harmonycloud.result.CimsResponseWrapper;
import com.harmonycloud.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ExceptionHandler {

    @Autowired
    HttpServletRequest request;
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public CimsResponseWrapper<Object> handler(Throwable e) {
        String msg = LogUtil.getRequest(request);

        logger.error(msg + ", information='" + e.getMessage() + '\'');
        if (e instanceof AuditException) {
            return new CimsResponseWrapper<>(false, e.getMessage(), null);
        }

        return new CimsResponseWrapper<>(false, ErrorMsgEnum.SERVICE_ERROR.getMessage(), null);
    }


}
