package com.microservices.delivery1.entites;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Ordered {
    public Ordered(){}
    public Ordered(Long id, Client client, List<Product> product, Double totalPrice){
        super();
        this.id = id;
        this.client = client;
        this.products = product;
        this.date = new Date();
        this.totalPrice = totalPrice;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional =  true)
    private Client client;

    @ManyToMany
    @Cascade(CascadeType.ALL)
    private List<Product> products;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date date;

    @Min(1)
    private Double totalPrice;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
