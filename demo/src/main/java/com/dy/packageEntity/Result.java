package com.dy.packageEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author: ZT
 * Date: 2020/5/22 20:19
 * Version 1.0
 *  * 数据接口返回对象
 * @author 18086
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /**
     * 响应数据
     */
    private Object data;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应编码
     * 100-请求成功
     * 101-请求异常
     * 103-未登录
     * 104-请求失败
     */
    private int code;
}
