package com.vlogplusplus.entity;

import java.util.Date;

public class User {
    private int u_id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String image;
    private int level;
    private int count_fan;
    private int count_follow;
    private byte sex;
    private Date birthday;
    private String fashion;
    private String medal;

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCount_fan() {
        return count_fan;
    }

    public void setCount_fan(int count_fan) {
        this.count_fan = count_fan;
    }

    public int getCount_follow() {
        return count_follow;
    }

    public void setCount_follow(int count_follow) {
        this.count_follow = count_follow;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFashion() {
        return fashion;
    }

    public void setFashion(String fashion) {
        this.fashion = fashion;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }
}
