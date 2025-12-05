package com.jkn.model.entity;

import java.sql.Blob;

public class GiftShopItem implements AbstractEntity {
    private Long id;
    private String name;
    private int price;
    private String description;
    private Blob picture;

    public GiftShopItem(String name, int price, String description, Blob picture) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.picture = picture;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getPicture() {
        return this.picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return id + ". " + name + ", Price: $" + (price / 100.0) + ", Desc: " + description;
    }
}
