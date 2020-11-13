/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.levi.springboot.i18n;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levi.springboot.utils.SpringBeanFactory;
import org.apache.commons.lang3.StringUtils;

/**
 * 国际化支持类接口
 *
 * @author kim.cheng
 * @history 2018-10-26
 */
public interface I18nSupport {

	//	@JsonIgnore
	String getI18nKey();

	@JsonIgnore
	String getLocalizedMessage();

	@JsonIgnore
	default String getI18nMessage() {
		try {
			I18nConfig i18nConfig = SpringBeanFactory.getBean(I18nConfig.class);
			if(!i18nConfig.isI18nEnabled()) {
				return this.getLocalizedMessage();
			}
			if(I18nLocaleHolder.getLocale() == null) {
				return this.getLocalizedMessage();
			}
			I18nProvider provider = SpringBeanFactory.getBean(I18nProvider.class);
			if(provider == null) {
				return this.getLocalizedMessage();
			}
//			String lang = I18nLocaleHolder.getLocale().getLanguage();
			String lang = I18nLocaleHolder.getLocale().toLanguageTag().toLowerCase();
			String i18nMessage = provider.getMessage(this.getI18nKey(), lang);
			//基础在查不到值时返回code，此处还原为null
			return i18nMessage != null && !StringUtils.equals(this.getI18nKey(), i18nMessage) ? i18nMessage : String.format("[D]%s[%s]",this.getI18nKey(), this.getLocalizedMessage());
		} catch (Exception e) {
			return String.format("[E]%s[%s]",this.getI18nKey(), this.getLocalizedMessage());
		}
	}
}
