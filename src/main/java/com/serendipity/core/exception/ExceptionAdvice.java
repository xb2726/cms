package com.serendipity.core.exception;

import com.serendipity.core.domain.ResultDTO;
import com.serendipity.core.enums.ResultCode;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther bin
 */
@ControllerAdvice
public class ExceptionAdvice {
    public static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(SystemException.class)
    public ResultDTO handleException(Exception e) {
        logger.error("系统异常信息：", e);
        ResultDTO result = new ResultDTO();
        if (e instanceof BusinessException) {
            e = (BusinessException) e;
            result.setCode(((BusinessException) e).getCode());
        }
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultDTO handleException(RuntimeException e) {
        logger.error("异常信息：", e.getMessage());
        ResultDTO result = new ResultDTO();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR.getCode());
        result.setMessage(e.getMessage());
        return result;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultDTO doBusinessException(Exception e) {
        ResultDTO result = new ResultDTO();
        result.setCode(ResultCode.FAILURE.getCode());
        result.setMessage(e.getMessage());
        return result;
    }
}
