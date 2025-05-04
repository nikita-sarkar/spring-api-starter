package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDTO;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDTO> getAllProducts(
            @RequestParam(required = false,  name = "categoryId")
            Byte categoryId)
    {
        List<Product> products;
        if(categoryId != null)
        {
            products = productRepository.findByCategoryId(categoryId);
        }
        else
        {
            products = productRepository.findAllWithCategory();
        }

        return products
                .stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id)
    {
        var product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return ResponseEntity.ok(productMapper.toDTO(product));
        }
    }


}
