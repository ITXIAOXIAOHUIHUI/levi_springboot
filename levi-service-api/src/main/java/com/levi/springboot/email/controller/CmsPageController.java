package com.levi.springboot.email.controller;

import com.levi.springboot.cms.CmsPageControllerApi;
import com.levi.springboot.common.response.QueryResponseResult;
import com.levi.springboot.common.response.ResponseResult;
import com.levi.springboot.model.cms.CmsPage;
import com.levi.springboot.model.cms.request.QueryPageRequest;
import com.levi.springboot.model.cms.response.CmsPageResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 17:24
 **/
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @GetMapping("/list/{page}/{size}")
    @Override
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {

        return null;
    }

    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        return null;
    }

    @Override
    public CmsPage findById(String id) {
        return null;
    }

    @Override
    public CmsPageResult edit(String id, CmsPage cmsPage) {
        return null;
    }

    @Override
    public ResponseResult delete(String id) {
        return null;
    }
}
