package com.bee.auto.dao;

import com.bee.auto.bean.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDAO {
   // void addProduct(Product product);
    List<Product> queryProList(Product product);
}
