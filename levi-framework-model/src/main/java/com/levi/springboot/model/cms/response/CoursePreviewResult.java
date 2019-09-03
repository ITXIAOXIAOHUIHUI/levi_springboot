package com.levi.springboot.model.cms.response;

import com.levi.springboot.common.response.ResponseResult;
import com.levi.springboot.common.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * Created by admin on 2018/3/5.
 */
@Data
@ToString
public class CoursePreviewResult extends ResponseResult {
    public CoursePreviewResult(ResultCode resultCode, String url) {
        super(resultCode);
        this.url = url;
    }

    String url;
}
