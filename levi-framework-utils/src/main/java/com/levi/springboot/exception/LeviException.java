package com.levi.springboot.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

/**
 * @author jianghaihui
 * @date 2020/10/21 10:39
 */
@Slf4j
public class LeviException extends Exception {

     private static final long serialVersionUID = 1L;

     @Getter
     private String code;

     @Getter
     private Object[] args;

    private static final String format(String message,Object ...args){
        if(StringUtils.isEmpty(message)){
            return message;
        }
        if(args == null  ||  args.length == 0){
            return message;
        }
        try{
            return MessageFormat.format(message,args);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return message;
        }
    }



    public LeviException(String code, String message, Throwable ex, Object... args) {
        super(format(message, args), ex);
        this.code = code;
        this.args = args;
    }

}
