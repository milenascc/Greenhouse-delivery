package com.microservices.delivery1.load;

import com.microservices.delivery1.entites.Client;
import com.microservices.delivery1.entites.Ordered;
import com.microservices.delivery1.entites.Product;
import com.microservices.delivery1.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RepositoryLoadTest {
    private static final long ID_CLIENTE_FERNANDO = 11l;
    private static final long ID_CLIENTE_ZE_PEQUENO = 22l;

    private static final long ID_ITEM1 = 100l;
    private static final long ID_ITEM2 = 101l;
    private static final long ID_ITEM3 = 102l;

    private static final long ID_PEDIDO1 = 1000l;
    private static final long ID_PEDIDO2 = 1001l;
    private static final long ID_PEDIDO3 = 1002l;

    @Autowired
    private ClientRepository cr;
    @RequestMapping("/create")
    @ResponseBody
    public String create(String name, String adress) {
        String userId = "";
        try {
            Client testClient = new Client(name,adress);
            cr.save(testClient);

            userId = String.valueOf(testClient.getId());
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created with id = " + userId;
    }






}
