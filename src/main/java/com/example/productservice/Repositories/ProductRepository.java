package com.example.productservice.Repositories;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.projections.ProductWithTitleAndPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository//to create a reference
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long productId);
    //select * from products where id=productId

    @Override
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findByTitleContains(String str);
    // select * from products where title like '%str%'


    Optional<Product> findByCategory(Category category);

    Optional<Product> findByCategory_Id(Long categoryId);

    @Override
     Product save (Product product);

    @Override
    void deleteById(Long productId);


    @Query(value = "select p.title,p.price from products p where p.title = :title and p.price = :price",nativeQuery = true)
    List<ProductWithTitleAndPrice>getProductsWithTitleAndPrice(String title,Double price);



}
