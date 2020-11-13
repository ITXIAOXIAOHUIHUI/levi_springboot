/******************************************************************************
 * Copyright (C) 2019 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 * 版权所有 翻版必究 --kim.cheng
 *****************************************************************************/
package com.levi.springboot.i18n;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 国际化配置工具类
 *
 * @author kim.cheng
 * @since 2018-1-10
 */
@Data
@Component
public class I18nConfig {

	@Value("${i18n.enabled:false}")
	private boolean i18nEnabled;
}
