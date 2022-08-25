package com.springboot.levi.leviweb1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-22 15:56
 */
@Data
public class RowError {

    private int row;
    private List<ColumnError> columnErrList = new ArrayList<>();

    public RowError() {

    }

    public RowError(int row) {
        this.row = row;
    }

    public RowError(int row, int column, String err) {
        this.row = row;
        columnErrList.add(new ColumnError(column, err));
    }
}
