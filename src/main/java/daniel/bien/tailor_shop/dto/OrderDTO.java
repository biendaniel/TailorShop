package daniel.bien.tailor_shop.dto;

import daniel.bien.tailor_shop.model.order.OrderStatusName;
import daniel.bien.tailor_shop.model.product.Product;
import daniel.bien.tailor_shop.model.user.Customer;
import daniel.bien.tailor_shop.model.user.Dimensions;
import daniel.bien.tailor_shop.model.user.Employee;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private Integer id;

    private Integer totalPrice;

    private String comments;

    private OrderStatusName status;

    private Boolean paid;

    @Setter(AccessLevel.NONE)
    private CustomerDTO customer;

    @Setter(AccessLevel.NONE)
    private EmployeeDTO employee;

    @Setter(AccessLevel.NONE)
    private List<ProductDTO> products;

    private Dimensions dimensions;


    public void setCustomerDTO(Customer customer) {
        this.customer = new CustomerDTO();
        this.customer.setId(customer.getId());
        this.customer.setUser(customer.getUser());
    }

    public void setEmployeeDTO(Employee employee) {
        this.employee = new EmployeeDTO();
        this.employee.setId(employee.getId());
        this.employee.setUser(employee.getUser());
    }


    public void setProductDTOs(List <Product> products) {
        this.products = new LinkedList<>();
        products.forEach(product -> this.products.add(addProductDTO(product)));
    }
    public ProductDTO addProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setClothesStyle(product.getClothesStyle());
        productDTO.setComments(product.getComments());
        productDTO.setDimensions(product.getDimensions());
        productDTO.setPrice(product.getPrice());
        productDTO.setTextile(product.getTextile());
        return productDTO;
    }

    public OrderDTO() {
    }

}
