package com.example.demo.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 实体类
 *
 * @author bowensun
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer userId;

    @Column
    private String userName;

    @Column
    private String nickName;

    @Column
    private String phone;

    @Column
    private String password;

    @Column(insertable = false)
    private Date createTime;

    @Column
    private Date updateTime;

    public User(String userName, String nickName, String phone, String password) {
        this.userName = userName;
        this.nickName = nickName;
        this.phone = phone;
        this.password = password;
    }

    public User(Integer userId, String userName, String nickName, String phone, String password, Date createTime, Date updateTime) {
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.phone = phone;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}