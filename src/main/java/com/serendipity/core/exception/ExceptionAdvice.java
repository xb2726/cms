package com.serendipity.core.exception;

import cn.hutool.core.util.ArrayUtil;
import com.serendipity.core.domain.ResultDTO;
import com.serendipity.core.enums.ResultCode;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * @Auther bin
 */
@ControllerAdvice
public class ExceptionAdvice {
    public static Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    private final MessageSource messageSource;

    @Autowired
    public ExceptionAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultDTO.fail(ResultCode.INTERNAL_SERVER_ERROR));
    }

    private String processFieldErrors(List<FieldError> fieldErrors) {
        if (ArrayUtil.isEmpty(fieldErrors)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator var3 = fieldErrors.iterator();

            while(var3.hasNext()) {
                FieldError fieldError = (FieldError)var3.next();
                String localizedErrorMessage = this.resolveLocalizedErrorMessage(fieldError);
                sb.append(localizedErrorMessage);
                sb.append(";");
            }

            return sb.substring(0, sb.length() - 1);
        }
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage(fieldError, currentLocale);
    }

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleArgException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String message = this.processFieldErrors(fieldErrors);
        return ResponseEntity.status(HttpStatus.OK).body(ResultDTO.fail(ResultCode.PARAM_VALID_ERROR, message));
    }

    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleMissingException(MissingServletRequestParameterException ex) {
        String message = ex.getLocalizedMessage();
        return ResponseEntity.status(HttpStatus.OK).body(ResultDTO.fail(ResultCode.PARAM_MISS, message));
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<Object> handleArgException(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String message = this.processFieldErrors(fieldErrors);
        return ResponseEntity.status(HttpStatus.OK).body(ResultDTO.fail(ResultCode.PARAM_VALID_ERROR, message));
    }

    @ResponseBody
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        log.error(String.format("业务异常：%d,%s", ex.getCode(), ex.getMessage()));
        return ResponseEntity.status(HttpStatus.OK).body(ResultDTO.fail(ex.getCode(), ex.getMessage()));
    }
}
