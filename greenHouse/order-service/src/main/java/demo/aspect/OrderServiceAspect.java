package demo.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

import demo.exceptions.EmptyArgumentExcpetion;
import demo.exceptions.InvalidArgumentException;
import demo.model.Order;

@Aspect
public class OrderServiceAspect {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderServiceAspect.class);
	
	@Pointcut("execution(* *.createOrder(..)) && args(order)")
	void pointcutBeforeSaving(Order order) {}
	
	@Before("pointcutBeforeSaving(order)")
	public void beforeCreateOrder(Order order) throws EmptyArgumentExcpetion, InvalidArgumentException {
		
		if(order.getRestaurantId()==null || order.getItems().isEmpty()) {
			throw new EmptyArgumentExcpetion("Favor preencher todos os campos!");
		}
		if(order.getTotalPrice()<0) {
			throw new InvalidArgumentException("O preço total deve dar um valor positivo!");
		}
	}
	
	@AfterThrowing(pointcut="pointcutBeforeSaving(order)",throwing="e")
	public void afterThrowingExceptionCreatingOrder(Order order, Exception e) throws Exception{
		log.warn(e.getMessage());
	}
}
