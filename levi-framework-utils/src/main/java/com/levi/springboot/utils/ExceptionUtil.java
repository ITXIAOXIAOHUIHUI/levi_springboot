package com.levi.springboot.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author jianghaihui
 * @date 2019/9/4 13:48
 */
public class ExceptionUtil {
    private ExceptionUtil() {

    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
        }
        return sw.toString();
    }
}
