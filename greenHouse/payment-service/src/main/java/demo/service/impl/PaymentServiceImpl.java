package demo.service.impl;

import demo.model.CreditCardInfo;
import demo.model.Order;
import demo.model.Payment;
import demo.repository.OrderRepository;
import demo.repository.PaymentRepository;
import demo.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {
    private RestTemplate restTemplate;
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.restTemplate = new RestTemplate();
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @HystrixCommand(fallbackMethod = "processPaymentFallback")
    @Override
    public void processPayment(Payment payment) {
        payment = paymentRepository.save(payment);
        Order order = orderRepository.findOrderById(payment.getOrderId());
        order.setPaymentId(payment.getId());
        long deliveryTime = getDeliveryTime();
        order.setDeliveryTime(deliveryTime);
        orderRepository.save(order);
        restTemplate.postForLocation("http://order-complete-updater/api/orders", order);

    }

    public void processPaymentFallback(Payment payment) {
        System.out.println("Fallback method is called.");
    }

    private long getDeliveryTime() {
        Random random = new Random();
        int randomPeriod = 5 + random.nextInt(60 - 5 + 1);
        return System.currentTimeMillis() + randomPeriod * 60 * 1000;
    }
}
