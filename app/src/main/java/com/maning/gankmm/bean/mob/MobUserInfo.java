package com.maning.gankmm.bean.mob;

import java.io.Serializable;

/**
 * Created by maning on 2017/7/12.
 */

public class MobUserInfo implements Serializable{


    private String userName;
    private String userPsd;
    private String userEmail;
    private String token;
    private String uid;
    private String avatar;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "MobUserInfo{" +
                "userName='" + userName + '\'' +
                ", userPsd='" + userPsd + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
