package demo.aspects;

import demo.model.CreditCardInfo;
import demo.model.Order;
import demo.model.Payment;
import demo.repository.OrderRepository;
import demo.service.impl.PaymentServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Aspect
public class AspectPaymentService {
	@Autowired
	private OrderRepository orderRepository;

	@Pointcut("execution(* *.processPayment(p)) && args(p) && target(service)")
	private void pointcutProcessPayment(PaymentServiceImpl service, Payment p){ }

	private boolean validateCreditCardInfo(CreditCardInfo creditCardInfo) {
	    return true;
	}

	@Around("pointcutProcessPayment(service,p)")
    public void around(ProceedingJoinPoint pjp, PaymentServiceImpl service, Payment p) throws Throwable{
		String errormsg = "";
		String orderId = p.getOrderId();
		if(orderId==null) {
			errormsg = "Missing orderId in payment";
		}else{
			Order o = orderRepository.findOrderById(orderId);
			if(o==null) errormsg = "Failed to retrieve order for orderId: " + orderId;
			else if(p.getAmount()<o.getTotalPrice()) errormsg = "Payment amount: " + p.getAmount() + " doesn't match with order price: " +
                    o.getTotalPrice() + ", orderId = " + orderId;
			else if(!validateCreditCardInfo(p.getCreditCardInfo())) errormsg = "Invalid credit card information for orderId: " + orderId;
		}
		if(errormsg.equals("")) {
			RestTemplate restTemplate = new RestTemplate();
			String orderCompleteUpdater = "http://order-complete-updater";
	        //log.warn(errorMessage);
	        restTemplate.postForLocation(orderCompleteUpdater + "/api/errors", errormsg);
		}else{
			try{
				pjp.proceed(new Object[]{service,p});
			}catch(Throwable t){
				t.printStackTrace();;
			}

		}
	}
}
