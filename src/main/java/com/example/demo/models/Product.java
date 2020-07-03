package com.example.demo.models;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column
    private float price;

    @Column(columnDefinition="TEXT")
    private String img;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Product() {
    }

    public Product(long id, String name, String description, float price, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public Product(String name, String description, float price, String img) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
