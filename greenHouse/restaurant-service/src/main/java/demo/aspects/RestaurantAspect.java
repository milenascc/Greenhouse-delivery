package demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import demo.exceptions.EmptyRestaurantNameException;
import demo.repository.RestaurantRepository;
import demo.service.impl.RestaurantServiceImpl;

@Aspect
public class RestaurantAspect {
//	@Autowired
//	private RestaurantRepository restaurantRepository;
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RestaurantAspect.class);
	
	@Pointcut("execution(* *.getRestaurantByName(String)) && args(name) && target(callee)")
	void beforeFindRestaurantByName(String name, RestaurantServiceImpl callee) {}

	@Before("beforeFindRestaurantByName(name,callee)")
	public void findRestaurantByName(String name, RestaurantServiceImpl callee) throws EmptyRestaurantNameException{
		log.info(name);
		if(name.length()==0 || name==null) {
			EmptyRestaurantNameException ere = new EmptyRestaurantNameException("Nome não fornecido para fazer a busca!");
			System.out.println(ere.getMessage());
		}
		System.out.println(name);
	}
	
}
