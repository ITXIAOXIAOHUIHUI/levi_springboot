package com.levi.springboot.cms;

import com.levi.springboot.common.response.QueryResponseResult;
import com.levi.springboot.common.response.ResponseResult;
import com.levi.springboot.model.cms.CmsPage;
import com.levi.springboot.model.cms.request.QueryPageRequest;
import com.levi.springboot.model.cms.response.CmsPageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author jianghaihui
 * @date 2019/9/2 13:48
 */
@Api(tags = "cms页面管理接口")
public interface CmsPageControllerApi {
    /**
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    //新增页面
    @ApiOperation("新增页面")
    public CmsPageResult add(CmsPage cmsPage);

    //根据页面id查询页面信息
    @ApiOperation("根据页面id查询页面信息")
    public CmsPage findById(String id);

    //修改页面
    @ApiOperation("修改页面")
    public CmsPageResult edit(String id, CmsPage cmsPage);

    //删除页面
    @ApiOperation("删除页面")
    public ResponseResult delete(String id);
}
