package com.app.persistence.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Builder
@ToString
@Getter
public class Car {

    private final String model;
    private final BigDecimal price;
    private final Color color;
    private final int mileage;
    private List<String> components;

    public boolean hasHigherMileage(int mileage) {
        return this.mileage > mileage;
    }

    public boolean hasPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return minPrice.compareTo(price) <= 0 && maxPrice.compareTo(price) >= 0;
    }

    public boolean containsComponent(String component) {
        return components.contains(component);
    }

    public Car getCarWithSortedComponents() {
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
