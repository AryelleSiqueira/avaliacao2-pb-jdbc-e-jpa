package br.com.compass.pb.avaliacao2.questao09.model;

import jdk.jfr.Unsigned;

import java.sql.Date;


public class Product {

    private Integer id;
    private final String name;
    private final String description;
    private final float price;
    private final float discount;
    private final Date date;


    public Product(String name, String description, float price, float discount, Date date) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.date = date;
    }

    public Product(Integer id, String name, String description, float price, float discount, Date date) {
        this(name, description, price, discount, date);
        this.setId(id);
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return this.discount;
    }

    public Date getDate() {
        return this.date;
    }

    public void setId(Integer id) {
        if (id < 0) {
            throw new NegativeIDException("ID cannot be < 0");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Product #%02d: %n\t%s%n\tDescription: %s%n\tPrice: %.2f%n\tDiscount: %.1f%%%n\tStart date: %s",
                this.id, this.name, this.description,this.price, this.discount*100, this.getDate());
    }
}
