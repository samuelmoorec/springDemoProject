package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.repos.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepo productDao;

    ProductController(ProductRepo productDao){
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public String viewProducts(Model model){

        List<Product> productList = productDao.findAll();

        model.addAttribute("products", productList );
        return "product/products";
    }

    @GetMapping("/product/create")
    public String showProductCreationForm(){
        return "product/create";
    }

    @PostMapping("/product/create")
    public String createProduct(
            @RequestParam(name = "product_name") String productName,
            @RequestParam(name = "product_description") String productDescription,
            @RequestParam(name = "product_price") float productPrice,
            @RequestParam(name = "product_image_url") String productImgUrl
    ){
        Product productToCreate = new Product(productName,productDescription,productPrice,productImgUrl);
        Product productInDb = productDao.save(productToCreate);
        return "redirect:/product/" + productInDb.getId();
    }

    @GetMapping("/product/{product_id}")
    public String viewOneProduct(@PathVariable Long product_id, Model model){
        Product current_Product = productDao.getOne(product_id);
        model.addAttribute("product", current_Product);
        return "product/product";
    }

    @GetMapping("/product/{product_id}/edit")
    public String showEditProductForm(@PathVariable Long product_id, Model model){
        Product productToEdit = productDao.getOne(product_id);
        model.addAttribute("product", productToEdit);
        return "product/edit";
    }

    @PostMapping("/product/{product_id}/edit")
    public String editProduct(
            @PathVariable Long product_id,
            @RequestParam(name = "product_name") String productName,
            @RequestParam(name = "product_description") String productDescription,
            @RequestParam(name = "product_price") float productPrice,
            @RequestParam(name = "product_image_url") String productImgUrl
    ){
        Product updatedProduct = new Product(

                product_id,
                productName,
                productDescription,
                productPrice,
                productImgUrl
        );

        productDao.save(updatedProduct);
        return "redirect:/product/" + product_id;

    }

    @GetMapping("/product/{product_id}/delete")
    public String showDeleteProductForm(@PathVariable Long product_id, Model model){
        Product productToEdit = productDao.getOne(product_id);
        model.addAttribute("product", productToEdit);
        return "product/delete";
    }

    @PostMapping("/product/{product_id}/delete")
    public String editProduct(@PathVariable Long product_id){
        productDao.deleteById(product_id);

        return "redirect:/products";
    }



}
