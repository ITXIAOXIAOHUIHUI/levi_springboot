package com.levi.springboot.email.mapper;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jianghaihui
 * @date 2020/1/10 16:59
 */
@Mapper
public interface EmailService {
    /**
     *
     * @param emailInfo
     */
    public Integer insertServer(EmailInfo emailInfo);
}
