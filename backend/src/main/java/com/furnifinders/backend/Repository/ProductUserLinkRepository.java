package com.furnifinders.backend.Repository;

import com.furnifinders.backend.Entity.ProductUserLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUserLinkRepository extends JpaRepository<ProductUserLink, Long> {

}
