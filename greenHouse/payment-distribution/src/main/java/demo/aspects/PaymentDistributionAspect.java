package demo.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

import demo.exceptions.EmptyInputFieldsException;
import demo.model.Payment;

@Aspect
public class PaymentDistributionAspect {
	
	@Pointcut("execution(public void *.pay(..)) && args(payment)")
	public void pointcutDistributePayment(Payment payment) {}
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(PaymentDistributionAspect.class);
	
	@Before("pointcutDistributePayment(payment)")
	public void beforeDistributePayment(Payment payment) throws EmptyInputFieldsException {
		
		if(payment == null || payment.getCreditCardInfo()==null) {
			throw new EmptyInputFieldsException("Please fill the required input fields (total amount, credit card info)");
		}
	}
	
	@AfterThrowing(pointcut="pointcutDistributePayment(payment)",throwing="ex")
	public void afterThrowingExcpetion(Payment payment, Exception ex) throws Exception{
		log.info(payment.toString());
	}
}
