package com.springboot.levi.leviweb1.design.factorymoshi;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-30 15:32
 */
public class ElfKing implements King {
    static  final String DESCRIPTION = "This is the elven king!";
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
