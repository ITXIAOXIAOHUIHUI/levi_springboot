package com.springboot.levi.leviweb1.design.factorymoshi;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-30 15:24
 */
public class ElfCastle implements Castle {
    static final String DESCRIPTION = "This is the elven castle";
    @Override
    public String getDecription() {
        return DESCRIPTION;
    }
}
