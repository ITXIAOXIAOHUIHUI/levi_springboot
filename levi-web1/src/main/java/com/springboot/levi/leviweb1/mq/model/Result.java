package com.springboot.levi.leviweb1.mq.model;

/**
 * @author jianghaihui
 * @date 2020/11/5 16:33
 */
public class Result<T> {
    /**
     * DEFAULT_CODE_SUCCESS
     */
    private static final String DEFAULT_CODE_SUCCESS = "200";

    /**
     * DEFAULT_CODE_FAILURE
     */
    private static final String DEFAULT_CODE_FAILURE = "500";

    /**
     * code
     */
    private final String code;

    /**
     * data
     */
    private final T data;

    /**
     * message
     */
    private final String message;

    /**
     * success tag
     */
    private final boolean success;

    private Result(String code, T data, String message, boolean success) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    //---- static methods starts here -----------

    /**
     * 构造Result
     *
     * @param code
     * @param data
     * @param message
     * @param success
     * @return
     */
    public static <T> Result<T> build(String code, T data, String message, boolean success) {
        return new Result<T>(code, data, message, success);
    }

    public static <T> Result<T> success(T data, String message) {
        return build(DEFAULT_CODE_SUCCESS, data, message, true);
    }

    public static <T> Result<T> success(T data) {
        return build(DEFAULT_CODE_SUCCESS, data, null, true);
    }

    public static Result<Void> success() {
        return build(DEFAULT_CODE_SUCCESS, null, null, true);
    }

    public static Result<Void> failure(String message) {
        return build(DEFAULT_CODE_FAILURE, null, message, false);
    }

    public static Result<Void> failure(String message, String code) {
        if (code == null) {
            code = DEFAULT_CODE_FAILURE;
        }
        return build(code, null, message, false);
    }
}

