package com.igool.dzda.job.model;

import java.util.Date;

/**
 * @author H J .
 * @date 2017/12/19.
 */
public class CountImagePhoto {

    private int id;
    private int count;
    private Date dateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "CountImagePhoto{" +
                "id=" + id +
                ", count=" + count +
                ", dateTime=" + dateTime +
                '}';
    }
}
