package com.vlogplusplus.entity;

import java.util.Date;

public class Draft {
    private int id;
    private int video_id;
    private Date u_time;
    private String draft_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public Date getU_time() {
        return u_time;
    }

    public void setU_time(Date u_time) {
        this.u_time = u_time;
    }

    public String getDraft_image() {
        return draft_image;
    }

    public void setDraft_image(String draft_image) {
        this.draft_image = draft_image;
    }
}
