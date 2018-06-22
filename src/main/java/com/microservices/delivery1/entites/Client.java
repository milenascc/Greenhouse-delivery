package com.microservices.delivery1.entites;


import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "Client")
public class Client {
    public Client(String name, String adress){
        this.name = name;
        this.adress = adress;
    }
    public Client(){}
    public Client(Long id) {
        this.id = id;
    }
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;

    @NotNull
    @Length(min=2, max = 30)
    private String name;

    @NotNull
    @Length(min=2, max = 300)
    private String adress;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<Ordered> ordereds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Ordered> getOrdereds() {
        return ordereds;
    }

    public void setOrdereds(List<Ordered> ordereds) {
        this.ordereds = ordereds;
    }

    public void newOrder(Ordered ordered){
        if(this.ordereds == null) ordereds = new ArrayList<Ordered>();
        ordereds.add(ordered);
    }
}
