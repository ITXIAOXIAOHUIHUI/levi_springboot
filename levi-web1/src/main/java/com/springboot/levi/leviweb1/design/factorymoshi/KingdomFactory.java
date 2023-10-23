package com.springboot.levi.leviweb1.design.factorymoshi;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-30 14:45
 */
public interface KingdomFactory {

    Castle createCastle();

    King createKing();

    Army createArmy();
}
