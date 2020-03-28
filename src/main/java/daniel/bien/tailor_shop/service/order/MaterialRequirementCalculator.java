package daniel.bien.tailor_shop.service.order;

import daniel.bien.tailor_shop.model.product.ProductType;
import daniel.bien.tailor_shop.model.user.Dimensions;
import net.bytebuddy.implementation.bytecode.Throw;

public class MaterialRequirementCalculator {

    public static int getMaterialValue(Dimensions dimensions, ProductType productType) {
        switch (productType.getName()) {
            case SHIRT:
                return calculateForShirt(dimensions);
            case TROUSER:
                return calculateForTrouser(dimensions);
            case JACKET:
                return calculateForJacket(dimensions);
            default:
                return 1;
        }
    }

    private static int calculateForTrouser(Dimensions dimensions) {
        return (int) (Math.round(dimensions.getLegLength() * 1.1 / 10) * 10);
    }

    private static int calculateForShirt(Dimensions dimensions) {
        return (int) (Math.round((dimensions.getTorsoLength() + 15) * 1.20 / 10) * 10);
    }

    private static int calculateForJacket(Dimensions dimensions) {
        return (int) (Math.round((dimensions.getTorsoLength() + 25) * 1.20 / 10) * 10);
    }
}
