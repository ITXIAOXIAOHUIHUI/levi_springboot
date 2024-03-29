package com.levi.springboot.model.cms.response;

import com.levi.springboot.common.response.ResponseResult;
import com.levi.springboot.common.response.ResultCode;
import lombok.Data;

/**
 * Created by mrt on 2018/3/31.
 */
@Data
public class GenerateHtmlResult extends ResponseResult {
    String html;

    public GenerateHtmlResult(ResultCode resultCode, String html) {
        super(resultCode);
        this.html = html;
    }
}
