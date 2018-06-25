package com.microservices.delivery1.load;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.microservices.delivery1.entites.Product;
import com.microservices.delivery1.repositories.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private final ProductRepository productRepository;
	private final String ITEM_URI = "products/";
	
	public ProductController(ProductRepository productRepository){
		this.productRepository = productRepository;
	}
	
	@GetMapping("/")
	public ModelAndView list(){
		Iterable<Product> product = this.productRepository.findAll();
		return new ModelAndView(ITEM_URI + "list", "products", product);
	}
	
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Product product){
		return new ModelAndView(ITEM_URI + "view","product", product);
	}
	
	@GetMapping("/new")
	public String creatForm(@ModelAttribute Product product){
		return ITEM_URI + "form";
	}
	
	@PostMapping(params = "form")
	public ModelAndView create(@Valid Product product,BindingResult result,RedirectAttributes redirect) {
		if (result.hasErrors()) { return new ModelAndView(ITEM_URI + "form","formErrors",result.getAllErrors()); }
		product = this.productRepository.save(product);
		redirect.addFlashAttribute("globalMessage","Product gravado com sucesso");
		return new ModelAndView("redirect:/" + ITEM_URI + "{product.id}","product.id",product.getId());
	}
	
	@GetMapping(value = "delete/{id}")
	public ModelAndView remover(@PathVariable("id") Long id,RedirectAttributes redirect) {
		this.productRepository.deleteById(id);
		Iterable<Product> itens = this.productRepository.findAll();
		
		ModelAndView mv = new ModelAndView(ITEM_URI + "list","itens",itens);
		mv.addObject("globalMessage","Product removido com sucesso");
	
		return mv;
	}

	@GetMapping(value = "update/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Product product) {
		return new ModelAndView(ITEM_URI + "form","product",product);
	}
}