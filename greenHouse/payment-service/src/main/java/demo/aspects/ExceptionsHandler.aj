package demo.aspects;
import org.springframework.web.client.RestTemplate;

import demo.model.Payment;

public aspect ExceptionsHandler{
	//pointcut processingPayment(): ;
	private final String orderCompleteUpdater = "http://order-complete-updater";
	
	 private void sendErrorMessage(String errorMessage) {
		RestTemplate restTemplate = new RestTemplate();
        //log.warn(errorMessage);
        restTemplate.postForLocation(orderCompleteUpdater + "/api/errors", errorMessage);
    }
	
	Object around(Payment p): execution(* *.processPayment(Payment)) && args(p){
		Object ret = null;
		String errormsg = "";
		if(p.getOrderId()==null) errormsg = "Missing orderId in payment";
		
		//else 
		return ret;
	}
}