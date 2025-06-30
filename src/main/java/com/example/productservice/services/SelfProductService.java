package com.example.productservice.services;

import com.example.productservice.Repositories.CategoryRepository;
import com.example.productservice.Repositories.ProductRepository;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Primary
@Service("selfProductService")

public  class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private Optional<Object> optionalCategory;

    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }



    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct=productRepository.findById(productId);

         if (optionalProduct.isEmpty()){
             throw new ProductNotFoundException("Product with id: "+productId+"doesnt exist");
         }
         return optionalProduct.get();

    }

    @Override
    public Page<Product> getAllProducts(int page, int size) {



        return productRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").ascending()));

    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
  Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());

        if (optionalCategory.isEmpty()) {
            // If the category does not exist, save it
            category = categoryRepository.save(category);
        } else {
            // If the category exists, use the existing one
            category = optionalCategory.get();
        }

        product.setCategory(category);
        return productRepository.save(product);


    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }


    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {

        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isEmpty()){

            throw new ProductNotFoundException("Product with id: " + id + "doesnt exist");
        }

        Product productFromDB=optionalProduct.get();


        productFromDB.setTitle(product.getTitle());
        productFromDB.setDescription(product.getDescription());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setImageUrl(product.getImageUrl());

    Category category = product.getCategory();

    if (category.getId() == null) {
        category= categoryRepository.save(category);
    }

    productFromDB.setCategory(category);

    return productRepository.save(productFromDB);
    }


    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
        // Check if product exists before deletion
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with id: " + id + " doesn't exist");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }


}
