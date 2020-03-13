package com.vlogplusplus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Complaint {
    private int id;
    private int u_id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date c_time;
    private String var;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
