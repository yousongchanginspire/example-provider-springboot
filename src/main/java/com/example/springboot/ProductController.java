package com.example.springboot;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
class ProductController implements DefaultApi {

  @GetMapping("/products")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> list = new ArrayList<Product>();   
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping({ "/product/{id}" })
  public ResponseEntity<Product> getProductByID(@PathVariable String id) {
    Product product = new Product();
    return new ResponseEntity<Product>(product, HttpStatus.OK);
  }
}