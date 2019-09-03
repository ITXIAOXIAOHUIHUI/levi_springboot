package com.levi.springboot.service;

import com.levi.springboot.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jianghaihui
 * @date 2019/9/2 17:24
 */
@Service
public class PageService {
    @Autowired
    CmsPageRepository cmsPageRepository;

}
