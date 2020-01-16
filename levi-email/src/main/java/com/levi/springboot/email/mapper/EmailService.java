package com.levi.springboot.email.mapper;

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
    public int insertServer(EmailInfo emailInfo);
}
