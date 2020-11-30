package com.example.smartcityc_22.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/29 at 11:26
 */
public class UserInfo implements Serializable {

    /**
     * id : 371402199902041133
     * name : 赵涵
     * avatar : http://192.168.155.106:8080/mobileA/images//user3.png
     * phone : 3333333358888
     * sex : male
     */

    private String id;
    private String name;
    private String avatar;
    private String phone;
    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
