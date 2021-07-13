/*
 * Copyright 2018 flashhold.com All right reserved. This software is the
 * confidential and proprietary information of flashhold.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with flashhold.com.
 */

package com.springboot.levi.leviweb1.service;

/**
 * das基类接口
 *
 * @param <T> DO type
 * @author jhh
 */
public interface BaseDas<T> {
    /**
     * 插入一条记录
     *
     * @param record to insert record
     * @return insert count
     */
    int insert(T record);

    /**
     * Update one record by id
     *
     * @param record to update record
     * @return update count
     */
    int updateByPrimaryKey(T record);

    /**
     * Delete one by id
     *
     * @param record to delete record
     * @return delete count
     */
    int delete(T record);
}
