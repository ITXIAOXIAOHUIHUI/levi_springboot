package com.levi.springboot.email.entity;

import lombok.Data;


@Data
public class CmsPageResult extends ResponseResult {
    public CmsPageResult(ResultCode resultCode) {
        super(resultCode);
    }
}
