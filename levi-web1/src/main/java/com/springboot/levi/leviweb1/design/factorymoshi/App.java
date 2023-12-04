package com.springboot.levi.leviweb1.design.factorymoshi;

import lombok.extern.slf4j.Slf4j;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-30 15:23
 */
@Slf4j
public class App implements Runnable {
    private final Kingdom kingdom = new Kingdom();

    public Kingdom getKingdom() {
        return kingdom;
    }

    @Override
    public void run() {
        LOGGER.info("elf kingdom");
        createKingdom(Kingdom.FacotryMaker.KingdomType.ELF);
        LOGGER.info(kingdom.getArmy().getDescription());
        LOGGER.info(kingdom.getCastle().getDecription());
        LOGGER.info(kingdom.getKing().getDescription());
        createKingdom(Kingdom.FacotryMaker.KingdomType.ORC);
    }

    private void createKingdom(Kingdom.FacotryMaker.KingdomType kingdomType) {
        KingdomFactory kingdomFactory = Kingdom.FacotryMaker.makeFactory(kingdomType);
        kingdom.setArmy(kingdomFactory.createArmy());
        kingdom.setCastle(kingdomFactory.createCastle());
        kingdom.setKing(kingdomFactory.createKing());
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
