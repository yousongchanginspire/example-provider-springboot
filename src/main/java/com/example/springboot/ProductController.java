package com.example.springboot;

import java.util.ArrayList;
import java.util.List;
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

  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> list = new ArrayList<Product>();   
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  public ResponseEntity<Product> getProductByID(@PathVariable String id) {
    Product product = new Product().id("1").name("name").type("type");
    return new ResponseEntity<Product>(product, HttpStatus.OK);
  }
}