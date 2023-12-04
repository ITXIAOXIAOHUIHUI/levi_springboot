package com.springboot.levi.leviweb1.design.factorymoshi;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-30 15:33
 */
public class OrcKing implements King {
    static final String DESCRIPTION = "This is the orc king!";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
