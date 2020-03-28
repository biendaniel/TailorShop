package daniel.bien.tailor_shop.service.order;

import daniel.bien.tailor_shop.dto.OrderDTO;
import daniel.bien.tailor_shop.model.order.EmailParameters;
import daniel.bien.tailor_shop.model.order.Order;
import daniel.bien.tailor_shop.model.order.OrderStatusName;
import daniel.bien.tailor_shop.model.product.Product;
import daniel.bien.tailor_shop.repository.order.OrderRepository;
import daniel.bien.tailor_shop.model.user.Dimensions;
import daniel.bien.tailor_shop.service.product.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    private EmailSender emailSender;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public int calculateTotalPrice(Dimensions dimensions, List<Product> products) {
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        return products.stream().mapToInt(product -> (int) materialRequirementCalculator.getMaterialValue(dimensions, product.getProductType())).sum();
    }

    public Order prepareNewOrder(Order order) {
        order.setStatus(OrderStatusName.CREATED);
        return order;
    }

    public List<OrderDTO> convertToOrderDTOCollection(Iterable<Order> orders) {
        List<OrderDTO> orderDTOs = new LinkedList<>();
        orders.forEach(order -> {
            if (order.getCustomer() != null) {
                orderDTOs.add(convertToOrderDTO(order));
            }
        });

        return orderDTOs;
    }

    private OrderDTO convertToOrderDTO(Order order) {
        try {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setComments(order.getComments());
            if (order.getEmployee() != null) {
                orderDTO.setEmployeeDTO(order.getEmployee());
            }
            orderDTO.setPaid(order.getPaid());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setDimensions(order.getDimensions());
            orderDTO.setTotalPrice(order.getTotalPrice());
            if (order.getCustomer() != null) {
                orderDTO.setCustomerDTO(order.getCustomer());
            }
            if (order.getProducts() != null) {
                orderDTO.setProductDTOs(order.getProducts());
            }
            return orderDTO;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }


    public void sendEmailAfterFinishOrder(Order order) {
        EmailParameters param = new EmailParameters();
        param.setSubject("Zamówienie gotowe");
        param.setRecipient(order.getCustomer().getUser().getEmail());
        param.setContent("Zamówienei nr " + order.getId() + " jest gotowe do odbioru. Zapraszamy.");
        emailSender.sendEmail(param);
    }

    public Integer calculatePrice(Order order) {
        return order
                .getProducts()
                .stream()
                .mapToInt(product -> product
                        .getProductType()
                        .getBasicPrice() + (product.getTextile().getPriceForMeter() *  MaterialRequirementCalculator
                        .getMaterialValue(order.getDimensions(), product.getProductType())/100)).sum();
    }
}
