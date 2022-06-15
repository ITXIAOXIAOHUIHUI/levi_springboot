package com.springboot.levi.leviweb1.init;

import com.springboot.levi.leviweb1.enums.WcsLock;
import com.springboot.levi.leviweb1.lock.api.LockType;
import com.springboot.levi.leviweb1.lock.impl.local.SingletonLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-15 17:27
 */
@Component
public class WcsLockRegister {
    private static final Logger LOG = LoggerFactory.getLogger(WcsLockRegister.class);
    @PostConstruct
    public void init() {
        for (WcsLock wcsLock : WcsLock.values()) {
            boolean reg = SingletonLock.registerLockType(new LockType(wcsLock.name(), wcsLock.getPriority()),
                    wcsLock.getThreshold());
            LOG.info("register:{} {}", wcsLock, reg);
        }
    }
}
