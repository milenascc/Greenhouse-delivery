package demo.aspects;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import demo.exceptions.EmptyRestaurantNameException;
import demo.exceptions.MissingRequiredParameterException;
import demo.model.MenuItem;
import demo.repository.RestaurantRepository;
import demo.service.impl.RestaurantServiceImpl;

@Aspect
public class RestaurantAspect {
//	@Autowired
//	private RestaurantRepository restaurantRepository;
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RestaurantAspect.class);
	
	@Pointcut("execution(* *.getRestaurantByName(String)) && args(name) && target(callee)")
	void beforeFindRestaurantByName(String name, RestaurantServiceImpl callee) {}

	//método para tratar regras de negócio na criação de item do menu 
	@Around("call(* *.createMenuItem(MenuItem)) && args(menuItem)")
	public Object aroundCreateMenuItem(ProceedingJoinPoint pjp, MenuItem menuItem) throws Throwable{
		Object ret = null;
		String errormsg = "";
		List<MenuItem> m = new ArrayList<MenuItem>();
		m.add(menuItem);
		errormsg = dealWithOneOrMoreItems(m);
		
		if(errormsg.length()>0) {
			log.warn("Faltou parâmetro");
			throw new MissingRequiredParameterException(errormsg);
		}else {
			ret = pjp.proceed(new Object[] {menuItem});
			log.info("Item criado!");
		}
		
		return ret;
	}
	
	@Around("call(* *.uploadMenuItems(..)) && args(menuItems)")
	public Object aroundUpload(ProceedingJoinPoint pjp, List<MenuItem> menuItems) throws Throwable{
		Object ret = null;
		String errormsg = dealWithOneOrMoreItems(menuItems);
		
		if(errormsg.length()>0) {
			log.warn("Faltou parâmetro");
			throw new MissingRequiredParameterException(errormsg);
		}else {
			ret = pjp.proceed(new Object[] {menuItems});
			log.info("Item criado!");
		}
		
		return ret;
	}
	
	@Before("beforeFindRestaurantByName(name,callee)")
	public void findRestaurantByName(String name, RestaurantServiceImpl callee) throws EmptyRestaurantNameException{
		if(name==null || name.length()==0) {
			throw new EmptyRestaurantNameException("Nome não fornecido para fazer a busca!");
		}
	}
	
	//método genérico para tratar regras de negócio para criação de itens do menu
	//nas chamadas de createMenuItem ou upload (bulk)
	private String dealWithOneOrMoreItems(List<MenuItem> items) {
		String errormsg="";
		for(int i=0;i<items.size();i++) {
			MenuItem menuItem = items.get(i);
			if(menuItem.getName()==null || menuItem.getName().length()<3 || menuItem.getName().length()>100) errormsg = "Item "+i+": O nome deve conter de 3 a 100 caracteres!";
			if(menuItem.getRestaurantId()==null) errormsg += "Item "+i+": O item deve estar associado a um restaurante!";
			if(menuItem.getPrice()<0) errormsg += "Item "+i+": O preço deve ser positivo!";			
		}
		return errormsg;
	}
}
