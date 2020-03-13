package com.vlogplusplus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Activity {
    private int id;
    private String title;
    private String type;
    private String var;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date begin_time;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date end_time;
    private String method;
    private String image;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date c_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }
}
