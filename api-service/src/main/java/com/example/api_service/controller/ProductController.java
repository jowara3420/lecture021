package com.example.api_service.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.product_service.dto.CreateProductDto;
import com.example.product_service.dto.ProductDto;
import com.example.product_service.dto.UpdateProductDto;
import com.example.product_service.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController 
{
	private final ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductDto> getProdcut(@PathVariable("id") int id)
	{
		return ResponseEntity.ok(productService.getProduct(id));
	}	
	
	@PostMapping("/products")
	public ResponseEntity<Void> createProduct(@RequestBody @Valid CreateProductDto createProductDto)
	{
	    productService.createProduct(createProductDto);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createProductDto.getId()).toUri();
	    return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Void> updateProduct(@PathVariable("id") int id, @RequestBody @Valid UpdateProductDto updateProductDto)
	{
	    productService.updateProduct(id, updateProductDto);
	    return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id)
	{
	    productService.deleteProduct(id);
	    return ResponseEntity.ok().build();
	}
}
