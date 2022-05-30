package com.levi.design.pattern.lock;

import lombok.Data;
import lombok.Getter;

/**
 * @author jianghaihui
 * @date 2020/10/27 15:00
 */
@Data
public class PickingLockObjectFactory {
    /**
     * 缺拣重新生成和手动生成作业单的lock object
     */
    @Getter
    private final Object shortReGeneratorLock = new Object();


    private PickingLockObjectFactory() {
    }

    public static PickingLockObjectFactory getInstance() {
        return InnerPickingLockInstance.pickingLockObjectFactory;
    }

    private static class InnerPickingLockInstance {
        private static PickingLockObjectFactory pickingLockObjectFactory = new PickingLockObjectFactory();
    }


}
