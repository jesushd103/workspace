package com.example.easytravel.model;

import java.io.Serializable;

public class Business implements Serializable {
    private String id;
    private String name;
    private int rating;
    private Location location;
    private String price;
    private String phone;
    private int review_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
