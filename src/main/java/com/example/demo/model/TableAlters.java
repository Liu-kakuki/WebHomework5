package com.example.demo.model;

import com.example.demo.data.AddData;
import com.example.demo.data.TableData;
import com.example.demo.entity.Linker;

import java.util.List;
import java.util.Vector;

public class TableAlters {
    public static boolean checkValidAdd(TableData table, AddData info) {
        boolean isvalid = true;
        Vector<AddData> list = table.getTabledata();
        for (int i = 0; i < list.size() && isvalid; i++) {
            if (list.elementAt(i).getUsername().equals(info.getUsername()))
                isvalid = false;
        }
        return isvalid;
    }
    // 判断是否有重复的电话号码
    public static boolean hasTel(List<Linker> linkerList, String tel) {
        boolean hasTel = false;
        System.out.println("有重复的号码");
        for (int i = 0; i < linkerList.size() && !hasTel; i++) {
            if (linkerList.get(i).getTel().equals(tel))
                hasTel = true;
        }
        return hasTel;
    }
}
