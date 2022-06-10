package com.app.persistence.model;

import com.app.persistence.model.exception.CarException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class Car {

    private final String model;
    private final BigDecimal price;
    private final Color color;
    private final int mileage;
    private final List<String> components;

    public boolean hasMileageGreaterThan(int mileage) {
        if (mileage < 0) {
            throw new CarException("Mileage must be positive!");
        }
        return this.mileage > mileage;
    }

    public boolean hasPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new CarException("BigDecimal object is null");
        }
        if (minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new CarException("Given price must be positive");
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new CarException("minPrice cannot be greater than maxPrice");
        }
        return minPrice.compareTo(price) <= 0 && maxPrice.compareTo(price) >= 0;
    }

    public boolean containsComponent(String component) {
        if (component == null) {
            throw new CarException("component is null");
        }
        return components.contains(component);
    }

    public Car withSortedComponents() {
        return Car.builder()
                .model(model)
                .price(price)
                .color(color)
                .mileage(mileage)
                .components(getSortedComponents())
                .build();
    }

    private List<String> getSortedComponents() {
        return components.stream().sorted().toList();
    }

}
