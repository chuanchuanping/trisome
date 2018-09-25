package com.bee.auto.bean;

import lombok.Data;

@Data
public class Product {

    private Integer prId; // 商品的编号
    private String prName; //商品名字
    private String prContent; //
    private Double price ; //商品价格
    private Integer stockNum ; //库存
    private Integer saleNum ; //出售

}
