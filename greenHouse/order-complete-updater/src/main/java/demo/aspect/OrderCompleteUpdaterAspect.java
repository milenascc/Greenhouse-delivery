package demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

import demo.model.Order;

@Aspect
public class OrderCompleteUpdaterAspect {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderCompleteUpdaterAspect.class);

	@Pointcut("execution(* *.orderComplete(..)) && args(order)")
	public void pointcutBeforeCompletingOrder(Order order) {}
	
	@Pointcut("execution(* *.orderComplete(String)) && args(errorMessage)")
	public void pointcutBeforeThrowingErrorMsg(String errorMessage) {}
	
	@Before("pointcutBeforeCompletingOrder(order)")
	public void beforeCompletingOrder(Order order) {
		log.info("Receive order = " + order.toString());
		String warnings = "";
		
        if (order.getUserInfo().getId() == null) {
        	warnings.concat("Usuário não identificado!");
            order.getUserInfo().setId("");
        }
        if (order.getPaymentId() == null) {
        	warnings.concat("Pagamento não identificado!");
            order.setPaymentId("");
        }
        if (order.getSpecialNote() == null) {
        	warnings.concat("Sem observações no pedido");
            order.setSpecialNote("");
        }
        log.warn(warnings);
	}
	
	@Before("pointcutBeforeThrowingErrorMsg(errorMessage)")
	public void beforeThrowingErrorMsg(String errorMessage) {
		log.error("Receive error = " + errorMessage);
	}
}
