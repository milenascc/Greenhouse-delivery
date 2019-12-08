package demo.aspects;

import demo.model.CreditCardInfo;
import demo.model.Order;
import demo.model.Payment;
import demo.repository.OrderRepository;
import demo.service.impl.PaymentServiceImpl;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Aspect
public class AspectPaymentService {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(AspectPaymentService.class);
	@Autowired
	private OrderRepository orderRepository;

	@Pointcut("execution(* *.processPayment(..)) && args(p) && target(service)")
	private void pointcutProcessPayment(PaymentServiceImpl service, Payment p){ }

	//verificação de dados válidos de cartão de crédito
	private boolean validateCreditCardInfo(CreditCardInfo creditCardInfo) {
		Date current = new Date();
		
	    return (creditCardInfo.getExpiredMonth()>0 && creditCardInfo.getExpiredMonth()<13) &&
	    		(current.compareTo(new Date(creditCardInfo.getExpiredYear(),creditCardInfo.getExpiredMonth(),1))<=0) &&
	    		creditCardInfo.getSecurityCode()<1000 && creditCardInfo.getSecurityCode()>99 &&
	    		(creditCardInfo.getFirstName()!=null && creditCardInfo.getLastName()!=null && creditCardInfo.getFirstName().length()>0 
	    		&& creditCardInfo.getLastName().length()>0);
	}

	@Around("pointcutProcessPayment(service,p)")
    public void around(ProceedingJoinPoint pjp, PaymentServiceImpl service, Payment p) throws Throwable{
		try {
			//Aspecto criado para remoção do tratamento das regras de negócio do código do serviço para ser feito aqui
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
			t.printStackTrace();
		}
	}
	
	//Por ter sido identificado um problema em relação ao uso de RabbitMQ (broker de mensagens para serviço orientado a eventos) para transmitir o objeto do microsserviço PaymentDistribution para este
	//foi criado este aspecto para rastrear a mensagem de erro e de onde ela veio
	@AfterThrowing(pointcut="pointcutProcessPayment(service,p)", throwing="ex")
	public void throwingException(PaymentServiceImpl service, Payment p, Exception ex) throws Exception{
		log.error("Oops, we have a problem: "+ex.getMessage());
		log.info(ex.getLocalizedMessage());
	}
}
