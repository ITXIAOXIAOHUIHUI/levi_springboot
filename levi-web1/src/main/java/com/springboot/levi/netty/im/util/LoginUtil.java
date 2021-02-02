package com.springboot.levi.netty.im.util;

import com.springboot.levi.netty.im.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        System.out.println("loginAttr.get() != null;" +loginAttr.get() != null);
        return loginAttr.get() != null;
    }
}
