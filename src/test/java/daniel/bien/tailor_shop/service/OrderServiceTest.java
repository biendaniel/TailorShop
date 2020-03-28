package daniel.bien.tailor_shop.service;

import daniel.bien.tailor_shop.model.order.Order;
import daniel.bien.tailor_shop.model.product.Product;
import daniel.bien.tailor_shop.model.product.ProductType;
import daniel.bien.tailor_shop.model.product.ProductTypeName;
import daniel.bien.tailor_shop.model.product.Textile;
import daniel.bien.tailor_shop.model.user.Dimensions;
import daniel.bien.tailor_shop.service.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest extends BDDMockito {

    @InjectMocks
    private OrderService orderService;

    List<Product> products = new ArrayList<>();
    List<Textile> textiles = new ArrayList<>();
    Order order;

    Dimensions setDimensions() {
        Dimensions dimensions = new Dimensions();
        dimensions.setChestSize(98);
        dimensions.setCollarSize(39);
        dimensions.setLegLength(80);
        dimensions.setLegWidth(30);
        dimensions.setSleeveLength(68);
        dimensions.setShoulderWidth(30);
        dimensions.setWaistSize(100);
        dimensions.setTorsoLength(98);
        return dimensions;
    }


    @BeforeEach
    void setUp() {
        Order order = new Order();
        Textile textile1 = new Textile();
        Textile textile2 = new Textile();
        textile1.setPriceForMeter(30);
        textile2.setPriceForMeter(40);
        textiles.add(textile1);
        textiles.add(textile2);

        ProductType productType1 = new ProductType();
        productType1.setName(ProductTypeName.SHIRT);
        productType1.setBasicPrice(200);

        ProductType productType2 = new ProductType();
        productType2.setName(ProductTypeName.TROUSER);
        productType2.setBasicPrice(180);

        Product product = new Product();
        product.setTextile(textile1);
        product.setProductType(productType1);

        Product product2 = new Product();
        product2.setTextile(textile2);
        product2.setProductType(productType2);

        products.add(product);
        products.add(product2);

        order.setProducts(products);
        order.setDimensions(setDimensions());
        this.order = order;
    }

    @Test
    void calculateOrderPriceShouldBeEqual() {
        int result = orderService.calculatePrice(order);
        assertEquals(458, result);
    }

    @Test
    void calculateOrderPriceShouldNotBeEqual_Lower() {
        int result = orderService.calculatePrice(order);
        assertNotEquals(455, result);
    }

    @Test
    void calculateOrderPriceShouldNotBeEqual_Higher() {
        int result = orderService.calculatePrice(order);
        assertNotEquals(460, result);
    }



}