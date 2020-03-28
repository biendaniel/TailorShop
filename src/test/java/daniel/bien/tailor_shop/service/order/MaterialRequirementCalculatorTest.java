package daniel.bien.tailor_shop.service.order;

import daniel.bien.tailor_shop.model.product.ProductType;
import daniel.bien.tailor_shop.model.product.ProductTypeName;
import daniel.bien.tailor_shop.model.user.Dimensions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaterialRequirementCalculatorTest {

    Dimensions dimensions = new Dimensions();
    ProductType productType = new ProductType();

    @BeforeEach
    void setUp() {
    dimensions.setChestSize(98);
    dimensions.setCollarSize(39);
    dimensions.setLegLength(80);
    dimensions.setLegWidth(30);
    dimensions.setSleeveLength(68);
    dimensions.setShoulderWidth(30);
    dimensions.setWaistSize(100);
    dimensions.setTorsoLength(98);
    }

    @Test
    void checkCalculatorForTrouserShouldBeEqual() {
        productType.setName(ProductTypeName.TROUSER);
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        long result = materialRequirementCalculator.getMaterialValue(dimensions, productType);
        assertEquals(result, 90);
    }

    @Test
    void checkCalculatorForTrouserShouldBeNotEqual_Lower() {
        productType.setName(ProductTypeName.TROUSER);
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        long result = materialRequirementCalculator.getMaterialValue(dimensions, productType);
        assertNotEquals(result, 89);
    }

    @Test
    void checkCalculatorForTrouserShouldBeNotEqual_Higher() {
        productType.setName(ProductTypeName.TROUSER);
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        long result = materialRequirementCalculator.getMaterialValue(dimensions, productType);
        assertNotEquals(result, 91);
    }

    @Test
    void checkCalculatorForShirtShouldBeEqual() {
        productType.setName(ProductTypeName.SHIRT);
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        long result = materialRequirementCalculator.getMaterialValue(dimensions, productType);
        assertEquals(result, 140);
    }

    @Test
    void checkCalculatorForShirtShouldBeNotEqual_Lower() {
        productType.setName(ProductTypeName.SHIRT);
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        long result = materialRequirementCalculator.getMaterialValue(dimensions, productType);
        assertNotEquals(result, 139);
    }

    @Test
    void checkCalculatorForShirtShouldBeNotEqual_Higher() {
        productType.setName(ProductTypeName.SHIRT);
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        long result = materialRequirementCalculator.getMaterialValue(dimensions, productType);
        assertNotEquals(result, 141);
    }

    @Test
    void checkCalculatorForJacketShouldBeEqual() {
        productType.setName(ProductTypeName.JACKET);
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        long result = materialRequirementCalculator.getMaterialValue(dimensions, productType);
        assertEquals(result, 150);
    }

    @Test
    void checkCalculatorForJacketShouldBeNotEqual_Lower() {
        productType.setName(ProductTypeName.JACKET);
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        long result = materialRequirementCalculator.getMaterialValue(dimensions, productType);
        assertNotEquals(result, 149);
    }

    @Test
    void checkCalculatorForJacketShouldBeNotEqual_Higher() {
        productType.setName(ProductTypeName.JACKET);
        MaterialRequirementCalculator materialRequirementCalculator = new MaterialRequirementCalculator();
        long result = materialRequirementCalculator.getMaterialValue(dimensions, productType);
        assertNotEquals(result, 151);
    }
}