package com.agenciacristal.crud.product.controller;

import com.agenciacristal.crud.product.service.ProductService;
import com.agenciacristal.crud.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/products")
public class ProductController {

    private  final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping(path = "{productId}")
    public Optional<Product> getProduct(@PathVariable("productId") Long id){
        return productService.getProduct(id);
    }

    @PostMapping
    public ResponseEntity<Object> registrarProducto(@RequestBody Product product){
        return this.productService.newProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarProducto(@RequestBody Product product){
        return this.productService.updateProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> eliminar(@PathVariable("productId") Long id){
        return this.productService.deleteproduct(id);
    }

}
