package com.dy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: ZT
 * Date: 2020/5/13 13:14
 * Version 1.0
 * @author 18086
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String user;
    private String password;
    private String email;
}
