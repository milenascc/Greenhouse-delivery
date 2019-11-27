package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.model.Payment;
import demo.service.PaymentService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
@EnableBinding(Sink.class)
public class PaymentSink {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PaymentSink.class);
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void updatePayment(Payment payment) throws Exception {
        log.info("Payment received for orderId: " + payment.getOrderId());
        paymentService.processPayment(payment);
    }
}
