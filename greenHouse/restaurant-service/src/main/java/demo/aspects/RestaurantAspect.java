package demo.aspects;

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

	@Around("call(* *.createMenuItem(MenuItem)) && args(menuItem)")
	public Object aroundCreateMenuItem(ProceedingJoinPoint pjp, MenuItem menuItem) throws Throwable{
		Object ret = null;
		String errormsg="";
		if(menuItem.getName().length()<3 || menuItem.getName().length()>100) errormsg = "O nome deve conter de 3 a 100 caracteres!";
		if(menuItem.getRestaurantId()==null) errormsg.concat("O item deve estar associado a um restaurante!");
		if(menuItem.getPrice()<0) errormsg.concat("O preço deve ser positivo!");
		
		if(errormsg.length()>0) {
			log.warn("deu ruim");
			throw new MissingRequiredParameterException(errormsg);
		}else {
			ret = pjp.proceed(new Object[] {menuItem});
		}
		
		return ret;
	}
	
	@Before("beforeFindRestaurantByName(name,callee)")
	public void findRestaurantByName(String name, RestaurantServiceImpl callee) throws EmptyRestaurantNameException{
		if(name.length()==0 || name==null) {
			throw new EmptyRestaurantNameException("Nome não fornecido para fazer a busca!");
		}
	}
	
	
}
