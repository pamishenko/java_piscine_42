package edu.school21.models;

public class Product {
    private Long id;
    private String name;
    private Long price;

    public Product(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long identifier, String name, Long price) {
        this.id = identifier;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
