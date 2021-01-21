package com.springboot.levi.leviweb1.wcs;

import lombok.extern.slf4j.Slf4j;

/**
 * runnable基类，用于处理并发和异常
 *
 * @author Administrator
 */
@Slf4j
public abstract class AbstractRunnable implements Runnable {
    protected volatile boolean isRunning = false;

    public synchronized boolean occupy() {
        if (!isRunning) {
            isRunning = true;
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        log.info(this.getClass().getSimpleName() + " start.....");
        try {
            doRun();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            log.info(this.getClass().getSimpleName() + " end.....");
            isRunning = false;
            //MiscUtils.destroyCache();
        }
    }

    protected abstract void doRun();
}
