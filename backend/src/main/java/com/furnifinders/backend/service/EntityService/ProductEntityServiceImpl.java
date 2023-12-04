package com.furnifinders.backend.service.EntityService;

import com.furnifinders.backend.DAO.ProductDAO;
import com.furnifinders.backend.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductEntityServiceImpl implements ProductEntityService{
    private final ProductDAO productDAO;

    @Autowired
    public ProductEntityServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> findAllProductsByUserId(Long id) {
        return productDAO.findAllProductsByUserId(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productDAO.findAllProducts();
    }

    @Override
    public Product updatePostStatus(Long id) {
        return productDAO.updatePostStatus(id);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return this.productDAO.searchProducts(keyword);
    }


}
