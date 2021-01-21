package com.springboot.levi.leviweb1.enums;

import lombok.Data;

/**
 * @author jianghaihui
 * @date 2020/10/14 11:10
 */

public enum  ManyStateEnum {
    Lack("缺拣完成","PickingMissComplete"),
    Cancel("作业取消","PickingCancel"),
    ;

    private String name;
    private String instance;

    ManyStateEnum(String name, String instance) {
        this.name = name;
        this.instance = instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }




}
