package com.vlogplusplus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Topic_join {
    private int id;
    private int u_id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date c_time;
    private int topic_id;

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

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }
}
