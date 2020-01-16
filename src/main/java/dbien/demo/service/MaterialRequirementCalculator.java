package dbien.demo.service;

import dbien.demo.domain.ClothesStyle;
import dbien.demo.domain.Dimensions;
import dbien.demo.domain.Product;
import dbien.demo.domain.ProductType;

public class MaterialRequirementCalculator {

    public Float getMaterialValue(Dimensions dimensions, ProductType productType) {
        return 20.2F; // TODO Świetne miejsce na wzorzec projektowy - hmm straegia? Fasada?
                    // dodać metodą dla każdego z typów ubrania
    }

}
