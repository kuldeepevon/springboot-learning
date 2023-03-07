package com.microservices.order.services;

import com.microservices.order.dto.ProductDto;
import com.microservices.order.dto.ProductReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ProductProxy {
    @Value("${services.product}")
    private String serviceProductUrl;

    @Autowired
    RestTemplate restTemplate;

    ProductDto findById(String id) throws HttpMessageNotReadableException  {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ProductDto> entity = new HttpEntity<ProductDto>(headers);
        System.out.println("id = " + id);

//        var req = restTemplate.exchange("http://localhost:8080/product/" + id, HttpMethod.GET, entity, ProductDto.class);
        var req = restTemplate.exchange(serviceProductUrl + "/product/" + id, HttpMethod.GET, entity, ProductDto.class);
        System.out.println("req = " + req);
        return req.getBody();
//        return restTemplate.exchange("http://localhost:8080/products/" + id, HttpMethod.GET, entity, ProductDto.class).getBody();
    }

    ProductDto updateProduct(String id, ProductReqDto productDto) throws HttpMessageNotReadableException  {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("Product proxy " + id);
        System.out.println("Product proxy " + productDto);
        HttpEntity<ProductReqDto> entity = new HttpEntity<ProductReqDto>(productDto, headers);

//        return restTemplate.exchange("http://localhost:8080/product/" + id, HttpMethod.PUT, entity, ProductDto.class).getBody();
        return restTemplate.exchange(serviceProductUrl + "/product/" + id, HttpMethod.PUT, entity, ProductDto.class).getBody();
    }
}
