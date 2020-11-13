package com.levi.springboot.exception;

import lombok.Data;

/**
 * @author jianghaihui
 * @date 2020/1/10 11:01
 */
@Data
public class ValidatorException extends  Exception {

    private static final long serialVersionUID = 5047767081420494873L;
    private String errCode;
    private Object[] args;

    public ValidatorException(String errCode, Object[] args) {
        this.errCode = errCode;
        this.args = args;
    }
}
