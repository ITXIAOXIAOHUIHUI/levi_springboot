/******************************************************************************
 * Copyright (C) 2019 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.levi.springboot.i18n;

/**
 * 国际化信息查询接口
 *
 * @author kim.cheng
 * @history 2019-1-25
 */
public interface I18nProvider {

	/**
	 * 获取国际化信息
	 * @param i18nKey
	 * @param language
	 * @return
	 */
	String getMessage(String i18nKey, String language);
}
