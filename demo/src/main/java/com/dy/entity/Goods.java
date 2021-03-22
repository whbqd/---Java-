package com.dy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: ZT
 * Date: 2020/5/24 13:05
 * Version 1.0
 * @author 18086
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    /**
     * id 商品编号
     * name 商品名称
     * price 商品价格
     * type 商品类型
     */
    private Integer id;
    private String name;
    private Integer price;
    private String type;
}
