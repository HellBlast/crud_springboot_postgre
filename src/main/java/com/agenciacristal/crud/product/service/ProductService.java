package com.agenciacristal.crud.product.service;

import com.agenciacristal.crud.product.model.Product;
import com.agenciacristal.crud.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    HashMap<String, Object> datos;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id){
       return this.productRepository.findById(id);
    }
    
    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> res = productRepository.findProductByName(product.getName());
        datos = new HashMap<>();

        if(res.isPresent()){
            datos.put("error", true);
            datos.put("message", "Ya existe un producto con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        productRepository.save(product);
        datos.put("menssage", "Se guardado con éxito");
        datos.put("dato", product);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateProduct(Product product) {
        datos = new HashMap<>();
        productRepository.save(product);
        datos.put("menssage", "Se actualizo con exito");
        datos.put("dato", product);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteproduct(Long id){
        datos = new HashMap<>();
        boolean existe=this.productRepository.existsById(id);
        if(!existe){
            datos.put("error", true);
            datos.put("message", "No existe un porducto con ese ID");
            return  new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        productRepository.deleteById(id);
        datos.put("message", "Producto eliminado");
        return  new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
