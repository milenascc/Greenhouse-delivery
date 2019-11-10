package demo.aspects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import demo.model.CreditCardInfo;
import demo.model.Order;
import demo.model.Payment;
import demo.repository.OrderRepository;

public aspect ExceptionsHandler{
	@Autowired
	private OrderRepository orderRepository;
	
	private boolean validateCreditCardInfo(CreditCardInfo creditCardInfo) {
	    return true;
	}
	
	after(): execution(* *.processPayment(Payment)){
		System.out.println("foi");
	}
	 
	void around(Payment p): execution(* *.processPayment(Payment)) && args(p){
//		String errormsg = "";
//		String orderId = p.getOrderId(); 
//		if(orderId==null) {
//			errormsg = "Missing orderId in payment";
//		}else{
//			Order o = orderRepository.findOrderById(orderId);
//			if(o==null) errormsg = "Failed to retrieve order for orderId: " + orderId;
//			else if(p.getAmount()<o.getTotalPrice()) errormsg = "Payment amount: " + p.getAmount() + " doesn't match with order price: " +
//                    o.getTotalPrice() + ", orderId = " + orderId;
//			else if(!validateCreditCardInfo(p.getCreditCardInfo())) errormsg = "Invalid credit card information for orderId: " + orderId;
//		}
//		if(errormsg!="") {
//			RestTemplate restTemplate = new RestTemplate();
//			String orderCompleteUpdater = "http://order-complete-updater";
//	        //log.warn(errorMessage);
//	        restTemplate.postForLocation(orderCompleteUpdater + "/api/errors", errormsg);
//		}else{
//			proceed();
//		}
	}
}