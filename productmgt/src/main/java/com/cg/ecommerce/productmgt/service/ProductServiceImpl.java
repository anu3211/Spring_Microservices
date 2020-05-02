package com.cg.ecommerce.productmgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.ecommerce.productmgt.dao.IProductDao;
import com.cg.ecommerce.productmgt.entities.Product;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    private IProductDao dao;

    public IProductDao getDao() {
        return dao;
    }

    @Autowired
    public void setDao(IProductDao dao) {
        this.dao = dao;
    }

    @Override
    public Product add(Product product) {
        dao.add(product);
        return product;

    }

    @Override
    public List<Product> fetchAll() {
        List<Product> list = dao.fetchAll();
        return list;
    }

    @Override
    public Product findById(String id) {
        Product product=dao.findById(id);
        return product;
    }
}
