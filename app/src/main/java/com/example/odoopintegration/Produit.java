package com.example.odoopintegration;

public class Produit {
    private String name;
    private String list_price;
    private String id;

    public Produit(String name, String list_price, String id) {
        this.name = name;
        this.list_price = list_price;
        this.id = id;
    }

    public Produit() {
    }

    public String getName() {
        return name;
    }

    public String getList_price() {
        return list_price;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList_price(String list_price) {
        this.list_price = list_price;
    }

    public void setId(String id) {
        this.id = id;
    }
}
