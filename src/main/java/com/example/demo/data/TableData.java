package com.example.demo.data;

import java.io.Serializable;
import java.util.Vector;

public class TableData implements Serializable {
    private Vector<AddData> tabledata;

    public TableData() {
        tabledata = new Vector<AddData>();
    }

    public Vector<AddData> getTabledata() {
        return tabledata;
    }

    public void setTabledata(Vector<AddData> tabledata) {
        this.tabledata = tabledata;
    }
}
