package com.learning.sample.controllers;

import com.learning.sample.dto.ProductDto;
import com.learning.sample.interfaces.CategoryService;
import com.learning.sample.interfaces.ProductService;
import com.learning.sample.models.Product;
import com.learning.sample.services.CategoryServiceImpl;
import com.learning.sample.util.StorageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StorageService storageService;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products =  productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id ) {
        Product product =  productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> insertProduct( @RequestBody ProductDto productDto ) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product, "category");
        product.setCategory(categoryService.getCategory(productDto.getCategory()));
        Product savedproduct =  productService.insertProduct(product);
        return new ResponseEntity<>(savedproduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct( @PathVariable("id") String id, @RequestBody ProductDto productDto ) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product, "category");
        product.setCategory(categoryService.getCategory(productDto.getCategory()));
        Product savedproduct =  productService.updateProduct(id, product);
        return new ResponseEntity<>(savedproduct, HttpStatus.OK);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Product> updateProductImage( @PathVariable("id") String id, @RequestParam("file") MultipartFile file ) {
        Product product =  productService.getProduct(id);
        String fileName = storageService.store(file);
        product.setImage(fileName);
        product = productService.updateProduct(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Resource> getProductImage(@PathVariable("id") String id ) {
        Product product =  productService.getProduct(id);
        Resource file = storageService.loadAsResource(product.getImage());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct( @PathVariable("id") String id ) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
