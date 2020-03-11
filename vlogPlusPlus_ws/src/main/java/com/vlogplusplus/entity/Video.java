package com.vlogplusplus.entity;

import java.util.Date;

public class Video {
    private int id;
    private String title;
    private String type;
    private String var;
    private String subtitle;
    private String content;
    private int u_id;
    private int t_id;
    private Date c_time;
    private int count_likes;
    private int count_share;
    private int count_favorite;
    private int count_watch;
    private byte state;

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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }

    public int getCount_likes() {
        return count_likes;
    }

    public void setCount_likes(int count_likes) {
        this.count_likes = count_likes;
    }

    public int getCount_share() {
        return count_share;
    }

    public void setCount_share(int count_share) {
        this.count_share = count_share;
    }

    public int getCount_favorite() {
        return count_favorite;
    }

    public void setCount_favorite(int count_favorite) {
        this.count_favorite = count_favorite;
    }

    public int getCount_watch() {
        return count_watch;
    }

    public void setCount_watch(int count_watch) {
        this.count_watch = count_watch;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}
