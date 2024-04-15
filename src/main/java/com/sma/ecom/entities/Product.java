package com.sma.ecom.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Product implements Serializable{

    @Id
    private int id;
    private String name;
    private double price;
    private String size;
    private String colour;
    private String img;
    private String description;
    public Product(int id,String name, double price, String size, String colour) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.colour = colour;
    }
    public Product(int id) {
        this.id = id;
    }
    public Product() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getDescription() { return description;}
    public void setDescription(String description) {this.description = description;}
}

