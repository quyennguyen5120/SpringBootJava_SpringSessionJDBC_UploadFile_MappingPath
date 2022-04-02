package com.example.thuchanh_4_1_2022.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {
    private Integer id;
    private String name;
    private Integer type;
    private String image;
    private Double price;
    private Date craeteTime;
    private String createByUser;
    private int amount;
    private Boolean status;
    public static List<Product> Products = null;
    static {
        Products = new ArrayList<>();
        Products.add(new Product(1,"My",0,"anh1.jpg",30000D,new Date(2022,11,11),"admin",3,true));
        Products.add(new Product(2,"Com",0,"anh2.jpg",30000D,new Date(2022,11,30),"admin",3,true));
        Products.add(new Product(3,"Bim bim oishi",1,"anh1.jpg",30000D,new Date(2022,11,30),"admin",3,true));
        Products.add(new Product(4,"String",2,"anh2.jpg",30000D,new Date(2022,11,30),"admin",3,true));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCraeteTime() {
        return craeteTime;
    }

    public void setCraeteTime(Date craeteTime) {
        this.craeteTime = craeteTime;
    }

    public String getCreateByUser() {
        return createByUser;
    }

    public void setCreateByUser(String createByUser) {
        this.createByUser = createByUser;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }



    public Product() {
    }

    public Product(Integer id, String name, Integer type, String image, Double price, Date craeteTime, String createByUser, int amount, Boolean status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
        this.price = price;
        this.craeteTime = craeteTime;
        this.createByUser = createByUser;
        this.amount = amount;
        this.status = status;
    }
}
