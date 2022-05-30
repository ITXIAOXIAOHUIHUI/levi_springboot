/******************************************************************************
 * Copyright (C) 2019 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.levi.springboot.i18n;

import java.util.Locale;

/**
 * 语言区域持有者对象
 *
 * @author kim.cheng
 * @history 2019-1-25
 */
public class I18nLocaleHolder {

    private static final ThreadLocal<Locale> LOCALE_HOLDER = new ThreadLocal<>();

    private I18nLocaleHolder() {
    }

    public static void setLocale(Locale locale) {
        LOCALE_HOLDER.set(locale);
    }

    public static Locale getLocale() {
        return LOCALE_HOLDER.get();
    }

    public static void clear() {
        LOCALE_HOLDER.remove();
    }
}
