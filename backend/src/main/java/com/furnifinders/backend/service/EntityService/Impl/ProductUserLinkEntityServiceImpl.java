package com.furnifinders.backend.service.EntityService.Impl;

import com.furnifinders.backend.DAO.ProductUserLinkDAO;
import com.furnifinders.backend.Entity.Enum.UserType;
import com.furnifinders.backend.service.EntityService.ProductUserLinkEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductUserLinkEntityServiceImpl implements ProductUserLinkEntityService {
    private final ProductUserLinkDAO productUserLinkDAO;

    @Autowired
    public ProductUserLinkEntityServiceImpl(ProductUserLinkDAO productUserLinkDAO) {
        this.productUserLinkDAO = productUserLinkDAO;
    }

    @Override
    public Optional<UserType> findUserTypeByProductIdAndUserId(Long product_id, Long user_id) {
        return this.productUserLinkDAO.findUserTypeByProductIdAndUserId(product_id, user_id);
    }
}
