package com.agenciacristal.crud.product.repository;

import com.agenciacristal.crud.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{

    Optional<Product> findProductByName(String name);
}
