package com.levi.springboot.model;

/**
 * @author jianghaihui
 * @date 2020/10/30 15:20
 */
public class Response {


    private boolean success;
    private String export;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getExport() {
        return export;
    }

    public void setExport(String export) {
        this.export = export;
    }

    @Override
    public String toString() {
        return "Request{" +
                "success=" + success +
                ", export='" + export + '\'' +
                '}';
    }
}
