package com.igool.ssp.web.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2017/8/3.
 */
public class Page<T> implements Serializable {

    public int pageSize;
    public long totalSize;
    public List<T> list;
    public String result;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }



}
