package com.vlogplusplus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Fan {
    private int id;
    private int fan_id;
    private int up_id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date c_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFan_id() {
        return fan_id;
    }

    public void setFan_id(int fan_id) {
        this.fan_id = fan_id;
    }

    public int getUp_id() {
        return up_id;
    }

    public void setUp_id(int up_id) {
        this.up_id = up_id;
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }
}
