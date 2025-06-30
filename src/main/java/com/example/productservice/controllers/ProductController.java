package com.example.productservice.controllers;


import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/Products")
public class ProductController {

    private ProductService productService;

    public ProductController( ProductService productService){
        this.productService = productService;

    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id)throws ProductNotFoundException {

//        ResponseEntity responseEntity=null;
//        try {
//            Product product = productService.getProductById(id);
//            responseEntity=new ResponseEntity<>(product,HttpStatus.OK);
//        }catch(ProductNotFoundException e){
//            System.out.println(e.getMessage());
//          responseEntity=new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

      //  }


     return productService.getProductById(id);
    }

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam("pageNumber")int pageNumber , @RequestParam("pageSize") int pageSize){
        return productService.getAllProducts(pageNumber,pageSize);
    }
    public List<Product> getAllProducts(){
     return null;
}


@PostMapping
    public Product createproduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long productId,@RequestBody Product product){
        return null;
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long productId, @RequestBody Product product) throws ProductNotFoundException {

        return productService.replaceProduct(productId,product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException exception){
    return new ResponseEntity<>(
             exception.getMessage(),
             HttpStatus.SERVICE_UNAVAILABLE
     );
    }

}