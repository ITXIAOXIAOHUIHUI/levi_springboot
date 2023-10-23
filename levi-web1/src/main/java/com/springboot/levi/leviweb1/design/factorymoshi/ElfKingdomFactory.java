package com.springboot.levi.leviweb1.design.factorymoshi;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-30 15:23
 */

public class ElfKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }
    @Override
    public King createKing() {
        return new ElfKing();
    }

    @Override
    public Army createArmy() {
        return new ElfArmy();
    }
}
