package com.springboot.levi.leviweb1.design.factorymoshi;

import lombok.Data;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-30 15:48
 */
@Data
public class Kingdom {
    private King king;

    private Castle castle;

    private Army army;

    public static class FacotryMaker{

        public enum KingdomType{
            ELF, ORC
        }


        public static  KingdomFactory makeFactory(KingdomType type){
            switch (type){
                case ELF:
                    return new ElfKingdomFactory();
                case ORC:
                    return new OrcKingdomFactory();
                    default:
                        throw new IllegalArgumentException("KingdomType not supported.");
            }
        }
    }
}
