package dbien.demo.service;

import dbien.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    private Integer makeOrderPrice {
//        return 1;
//    }
//
}
