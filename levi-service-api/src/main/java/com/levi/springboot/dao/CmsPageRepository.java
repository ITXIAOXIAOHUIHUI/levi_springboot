package com.levi.springboot.dao;

import com.levi.springboot.model.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author jianghaihui
 * @date 2019/9/2 17:25
 */
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {

    /**
     * 根据页面名称查询
     */
    CmsPage findByPageName(String pageName);

    /**
     * 根据页面名称、站点Id、页面webpath查询
     */
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);


}
