package demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import demo.exceptions.EmptyArgumentExcpetion;
import demo.exceptions.InvalidArgumentException;
import demo.model.Order;

@Aspect
public class OrderServiceAspect {

	@Pointcut("execution(* *.createOrder(..)) && args(order)")
	void pointcutBeforeSaving(Order order) {}
	
	@Before("pointcutBeforeSaving(order)")
	public void beforeCreateOrder(Order order) throws EmptyArgumentExcpetion, InvalidArgumentException {
		//String errorStr = "";
		if(order.getPaymentId()==null || order.getRestaurantId()==null || order.getItems().isEmpty()) {
			throw new EmptyArgumentExcpetion("Favor preencher todos os campos!");
		}
		if(order.getTotalPrice()<0) {
			throw new InvalidArgumentException("O preço total deve dar um valor positivo!");
		}
	}
}
