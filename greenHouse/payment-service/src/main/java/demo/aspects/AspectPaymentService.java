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
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Aspect
//@Configuration
//@EnableAspectJAutoProxy
public class AspectPaymentService {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(AspectPaymentService.class);
	@Autowired
	private OrderRepository orderRepository;

	@Pointcut("execution(* *.processPayment(..)) && args(p) && target(service)")
	private void pointcutProcessPayment(PaymentServiceImpl service, Payment p){ }

	private boolean validateCreditCardInfo(CreditCardInfo creditCardInfo) {
	    return true;
	}

	@Around("pointcutProcessPayment(service,p)")
    public void around(ProceedingJoinPoint pjp, PaymentServiceImpl service, Payment p) throws Throwable{
		try {
//			log.info("entrei no aspecto \n"+p.getId()+"\n"+service.toString());
			String errormsg = "";
			String orderId = p.getOrderId();
			if(orderId==null) {
				errormsg = "Missing orderId in payment";
			}else{
				log.info("order repository"+orderRepository.toString());
				Order o = orderRepository.findOrderById(orderId);
				if(o==null) errormsg = "Failed to retrieve order for orderId: " + orderId;
				else if(p.getAmount()<o.getTotalPrice()) errormsg = "Payment amount: " + p.getAmount() + " doesn't match with order price: " +
	                    o.getTotalPrice() + ", orderId = " + orderId;
				else if(!validateCreditCardInfo(p.getCreditCardInfo())) errormsg = "Invalid credit card information for orderId: " + orderId;
			}
			if(!errormsg.equals("")) {
				RestTemplate restTemplate = new RestTemplate();
				String orderCompleteUpdater = "http://order-complete-updater";
		        log.warn(errormsg);
		        restTemplate.postForLocation(orderCompleteUpdater + "/api/errors", errormsg);
			}else{
				pjp.proceed(new Object[]{service,p});
				log.info("Payment succeed for order "+p.getOrderId());
			}
		}catch(Throwable t){
//			log.info(p.getId());
	//		log.error(t.getMessage());
			t.printStackTrace();
		}
	}
}
