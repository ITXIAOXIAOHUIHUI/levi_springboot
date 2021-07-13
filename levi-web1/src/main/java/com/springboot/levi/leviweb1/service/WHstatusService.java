package com.springboot.levi.leviweb1.service;

import com.springboot.levi.leviweb1.dto.domain.WhStatus;

import java.util.List;

/**
 * @author jianghaihui
 * @date 2020/11/10 14:43
 */
public interface WHstatusService extends BaseDas<WhStatus> {

    /**
     * @return
     */
    List<WhStatus> getAll();

    WhStatus queryOne(String line);

    int updateOne(WhStatus whStatus, String line);


}
