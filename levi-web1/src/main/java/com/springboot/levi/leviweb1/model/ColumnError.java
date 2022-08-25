package com.springboot.levi.leviweb1.model;

import lombok.Data;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-22 15:57
 */
@Data
public class ColumnError {
    private int inx;
    private String err;

    public ColumnError() {

    }

    public ColumnError(int inx, String err) {
        this.inx = inx;
        this.err = err;
    }
}
