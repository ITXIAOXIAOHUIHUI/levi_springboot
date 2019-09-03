package com.levi.springboot.model.cms.response;

import com.levi.springboot.common.response.ResponseResult;
import com.levi.springboot.common.response.ResultCode;
import com.levi.springboot.model.cms.CmsPage;
import lombok.Data;


@Data
public class CmsPageResult extends ResponseResult {
    CmsPage cmsPage;
    public CmsPageResult(ResultCode resultCode, CmsPage cmsPage) {
        super(resultCode);
        this.cmsPage = cmsPage;
    }
}
