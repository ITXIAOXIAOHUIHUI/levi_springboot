package com.springboot.levi.leviweb1.event;

import com.springboot.levi.leviweb1.enums.BizType;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-15 19:00
 */
public abstract  class LeviEvent extends ApplicationEvent {

    /**
     * 搬运单集合
     */
    private List<String> robotOrderIds;
    /**
     * 仓库id
     */
    private Long warehouseId;
    /**
     * 机器人编码
     */
    private String robotCode;

    /**
     * 构造器
     * @param source
     * @param robotCode
     * @param robotOrderIds
     * @param warehouseId
     */
    public LeviEvent(Object source,String robotCode, List<String> robotOrderIds, Long warehouseId) {
        super(source);
        this.robotOrderIds = robotOrderIds;
        this.warehouseId = warehouseId;
        this.robotCode = robotCode;
    }

    /**
     * 构造器
     * @param source
     * @param robotOrderIds
     * @param warehouseId
     */
    public LeviEvent(Object source,List<String> robotOrderIds, Long warehouseId) {
        super(source);
        this.robotOrderIds = robotOrderIds;
        this.warehouseId = warehouseId;
    }

    /**
     * 获取机器人搬运单id集合
     * @return
     */
    public List<String> getRobotOrderIds() {
        return this.robotOrderIds;
    }

    /**
     * 获取仓库id
     * @return
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 获取机器人编码
     * @return
     */
    public String getRobotCode(){
        return this.robotCode;
    }

    /**
     * 设置方法
     * @param robotCode
     */
    public void setRobotCode(String robotCode){
        this.robotCode = robotCode;
    }

    /**
     * 获取类型
     * @return
     */
    abstract public String getType();

    /**
     * toString方法
     * @return
     */
    @Override
    public String toString() {
        String context = String.join(",", robotOrderIds);
        return "Event Type: " + getType() + " Context: " + context + " Warehouse:" + warehouseId;
    }
}
