package com.bee.auto.service;

import com.bee.auto.bean.Product;
import com.bee.auto.dao.ProductDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopService {

    @Resource
    ProductDAO productDAO;

    public List<Product> getProList(){
         Product product = new Product() ;
         return productDAO.queryProList(product);
     }
}
