package com.springboot.levi.leviweb1.mq.consumer;

public enum ConsumerTag {
    UNKNOWN,

    ReplenishOrderCompleteEvent, // 入库单按单反馈
    ReplenishOrderFulfillDetailCreateEvent, // 入库单按上架明细反馈
    PickingOrderCompleteEvent, // 出库单按单反馈
    PickingOrderPackageCreateEvent, // 出库单按箱反馈
    PickingOrderFulfillDetailCreateEvent, // 出库单按拣选明细反馈
    CycleCountCompleteEvent, // 盘点单反馈
    InventoryAdjuestmentCompleteEvent, // 库存调整反馈

    JobReportEvent, // 货架到/搬运对象达目标区域回报接口（反馈）
    AgvStateReportEvent, // AGV状态定时上报接口（反馈）

    AgvOnOffEvent, // 上报agv上线下线事件
    AgvJobStateEvent, // 上报任务状态

    ;

}
