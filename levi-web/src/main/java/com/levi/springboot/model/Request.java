package com.levi.springboot.model;

/**
 * @author jianghaihui
 * @date 2020/10/30 15:20
 */
public class Request {

    /**
     * 运单号
     */
    private String waybillNos;

    /**
     * 设备ID
     */
    private String sbid;

    /**
     * 车间代码
     */
    private String cjdm;

    public String getWaybillNos() {
        return waybillNos;
    }

    public void setWaybillNos(String waybillNos) {
        this.waybillNos = waybillNos;
    }

    public String getSbid() {
        return sbid;
    }

    public void setSbid(String sbid) {
        this.sbid = sbid;
    }

    public String getCjdm() {
        return cjdm;
    }

    public void setCjdm(String cjdm) {
        this.cjdm = cjdm;
    }

    @Override
    public String toString() {
        return "Request{" +
                "waybillNos='" + waybillNos + '\'' +
                ", sbid='" + sbid + '\'' +
                ", cjdm='" + cjdm + '\'' +
                '}';
    }
}
