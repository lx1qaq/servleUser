package com.buercorp.longxiaolin.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 小林
 * Create on 2024/3/14 10:21
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String address;
    private String nickname;
    private String gender;
    private String email;
    private String status;//1 表示已激活  0表示未激活
}
