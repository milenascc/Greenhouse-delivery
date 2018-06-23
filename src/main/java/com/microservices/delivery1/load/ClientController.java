package com.microservices.delivery1.load;


import com.microservices.delivery1.entites.Client;
import com.microservices.delivery1.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ClientRepository clientRep;
    private final String CLIENTE_URI = "clientes/";

    @GetMapping("/")
    public ModelAndView list(){
        Iterable<Client> clients = this.clientRep.findAll();
        return new ModelAndView("clientes/list","clientes", clients);
    }

    @GetMapping("{id}")
    public	ModelAndView view(@PathVariable ("id")Client	client)	{
        return	new	ModelAndView("clientes/view","cliente",client);
    }
    @GetMapping("/novo")
    public	String	createForm(@ModelAttribute Client client) {
        return "cliente/form";
    }

    @PostMapping(params = "form")
    public ModelAndView create(@Valid Client client, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) { return new ModelAndView(CLIENTE_URI + "form","formErrors",result.getAllErrors()); }
        client = this.clientRep.save(client);
        redirect.addFlashAttribute("globalMessage","Cliente gravado com sucesso");
        return new ModelAndView("redirect:/" + CLIENTE_URI + "{cliente.id}","cliente.id",client.getId());
    }

    @GetMapping(value = "remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id,RedirectAttributes redirect) {
        //this.clientRep.delete(id);
        Iterable<Client> clients = this.clientRep.findAll();

        ModelAndView mv = new ModelAndView(CLIENTE_URI + "list","clientes",clients);
        mv.addObject("globalMessage","Cliente removido com sucesso");

        return mv;
    }

    @GetMapping(value = "alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Client client) {
        return new ModelAndView(CLIENTE_URI + "form","cliente",client);
    }






}
