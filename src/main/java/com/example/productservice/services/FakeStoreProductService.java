package com.example.productservice.services;

import com.example.productservice.Dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
//@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{


    //this service will use fakeStore to fetch product from fakeStore
   private RestTemplate restTemplate;
   private RedisTemplate<String, Object> redisTemplate;

   public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate){
       this.restTemplate=restTemplate;
       this.redisTemplate=redisTemplate;
   }

   private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product=new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category =new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);


        return product;


   }

    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        //to get getProductByID make A API call to fakeStore and get the product with given Id

        Product product = (Product) redisTemplate.opsForHash().get("products","PRODUCT " + productId );
       if( product !=null ){
           return product;
       }

        FakeStoreProductDto fakeStoreProductDto=restTemplate .getForObject(
                "https://fakestoreapi.com/products/"+
                        productId,FakeStoreProductDto.class);

        if(fakeStoreProductDto==null){
            throw new ProductNotFoundException("Product with id: " + productId +"doesnt exist");
        }

        product=convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" +  productId,product);
        return product;

        // throw new RuntimeException("Something went wrong");
   }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
       FakeStoreProductDto[] fakeStoreProductDtos=
               restTemplate.getForObject(
                       "https://fakestoreapi.com/products",
                       FakeStoreProductDto[].class
               );
       List<Product> products = new ArrayList<>();
       for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
           products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
       }
        return new PageImpl<>(products) {};
    }
    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        try {
            FakeStoreProductDto responseDto = restTemplate.exchange(
                    "https://fakestoreapi.com/products/" + id,
                    org.springframework.http.HttpMethod.PUT,
                    new org.springframework.http.HttpEntity<>(fakeStoreProductDto),
                    FakeStoreProductDto.class
            ).getBody();

            if (responseDto == null) {
                throw new RuntimeException("Failed to replace product with id: " + id);
            }

            return convertFakeStoreProductDtoToProduct(responseDto);
        } catch (Exception e) {
            throw new RuntimeException("Failed to replace product with id: " + id);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            restTemplate.delete("https://fakestoreapi.com/products/" + id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete product with id: " + id);
        }
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }


}
   