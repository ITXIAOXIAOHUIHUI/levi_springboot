package com.levi.springboot.utils;

import com.levi.springboot.common.response.CommonCode;
import com.levi.springboot.common.response.ResponseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianghaihui
 * @date 2019/9/15 14:46
 */
@ResponseBody
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    ResponseResult responseResult = new ResponseResult(CommonCode.INVALID_PARAM);
    ResponseResult unauthorise = new ResponseResult(CommonCode.UNAUTHORISE);

    //拦截所有异常
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseResult> exceptionHandler(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return ResponseEntity.status(400).body(responseResult);
        }
        return null;
    }

}
