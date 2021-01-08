package com.example.odoopintegration;

public class Client {
    private String Name;
    private String country_id;
    private String country_name;

    public Client(String name, String country_id, String country_name) {
        Name = name;
        this.country_id = country_id;
        this.country_name = country_name;
    }

    public Client() {
    }

    public String getName() {
        return Name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
