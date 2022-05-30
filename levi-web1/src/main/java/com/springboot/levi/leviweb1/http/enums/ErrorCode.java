package com.springboot.levi.leviweb1.http.enums;

public enum ErrorCode {
    SUCCESS("SUCCESS", true),
    Err_Internal("Internal Error: %s", true),
    Err_DataValidationFailed("Data validate failed: %s", false),
    Err_InvalidAppKey("AppKey invalid: %s", false),
    Err_InvalidSign("Sign check failed: %s", false),
    Err_InvalidOrExpiredAppSecret("AppSecret invalid: %s", false),
    Err_RequestDuplicate("Duplicate request: %s", false),
    Err_InvalidVersion("Request version invalid: %s", false),
    Err_RequestSizeOverLimit("Request size too big: %s", false),
    Err_RequestItemCountOverLimit("Request item overflow: %s", false),
    Err_RequestProcessFailed("Request process failed: %s", true),
    Err_InvalidHeader("Header invalid: %s", false),
    Err_InvalidRequestBody("Request body invalid: %s", false),
    Err_ConvertRequestBody("Request convert failed: %s", true),
    Err_InvalidResponseBody("Response body invalid: %s", false),
    Err_ConvertResponseBody("Response body convert failed: %s", true),
    Err_RewriteRequestBody("Request rewrite failed: %s", true),
    Err_RewriteResponseBody("Response body rewrite failed: %s", true),
    Err_DataFormat("Error data format", true),
    ;

    private final String desc;
    private final boolean internal;
    private ErrorCode(String desc, boolean internal) {
        this.desc = desc;
        this.internal = internal;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isInternal() {
        return internal;
    }
}
