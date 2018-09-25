package com.bee.auto.bean;

import lombok.Data;

import java.sql.Date;

@Data
public class SysUser {

    private Integer id;
    private String username;
    private String email;
    private String pswd;
    private Date  createTime;
    private String openid;
    private String headimgurl;
    private String nickname;
    private String city;
    private String province;
    private String country;
    private Integer sex;

}
