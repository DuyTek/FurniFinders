package com.furnifinders.backend.service.EntityService.Impl;

import com.furnifinders.backend.DAO.ProductDAO;
import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.service.EntityService.ProductEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductEntityServiceImpl implements ProductEntityService {
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
    public Product updateApprovePostStatus(Long id) {
        return productDAO.updateApprovePostStatus(id);
    }

    @Override
    public Product updateRejectPostStatus(Long id) {
        return productDAO.updateRejectPostStatus(id);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return this.productDAO.searchProducts(keyword);
    }

    @Override
    public Product findProductById(Long id) {
        return this.productDAO.findProductById(id);
    }

    @Override
    public Product addProductImage(Long id, String image) {
        return this.productDAO.addProductImage(id, image);
    }


}
