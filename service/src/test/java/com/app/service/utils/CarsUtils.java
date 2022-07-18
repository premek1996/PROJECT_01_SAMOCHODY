package com.app.service.utils;

import com.app.persistence.model.Car;
import com.app.persistence.model.Color;
import com.app.service.type.Statistics;

import java.math.BigDecimal;
import java.util.List;

public interface CarsUtils {
    Car BMW = Car
            .builder()
            .model("BMW")
            .price(BigDecimal.valueOf(120))
            .color(Color.BLUE)
            .mileage(1500)
            .components(List.of("ABS", "ALLOY WHEELS"))
            .build();

    Car BMW_WITH_SORTED_COMPONENTS = Car
            .builder()
            .model("BMW")
            .price(BigDecimal.valueOf(120))
            .color(Color.BLUE)
            .mileage(1500)
            .components(List.of("ABS", "ALLOY WHEELS"))
            .build();

    Car MERCEDES = Car
            .builder()
            .model("MERCEDES")
            .price(BigDecimal.valueOf(130))
            .color(Color.GREEN)
            .mileage(1700)
            .components(List.of("AIR CONDITIONING", "BLUETOOTH", "ABS"))
            .build();

    Car MERCEDES_WITH_SORTED_COMPONENTS = Car
            .builder()
            .model("MERCEDES")
            .price(BigDecimal.valueOf(130))
            .color(Color.GREEN)
            .mileage(1700)
            .components(List.of("ABS", "AIR CONDITIONING", "BLUETOOTH"))
            .build();

    Car MAZDA = Car
            .builder()
            .model("MAZDA")
            .price(BigDecimal.valueOf(160))
            .color(Color.RED)
            .mileage(2500)
            .components(List.of("BLUETOOTH", "AIR CONDITIONING"))
            .build();

    Car MAZDA_WITH_SORTED_COMPONENTS = Car
            .builder()
            .model("MAZDA")
            .price(BigDecimal.valueOf(160))
            .color(Color.RED)
            .mileage(2500)
            .components(List.of("AIR CONDITIONING", "BLUETOOTH"))
            .build();

    Statistics STATISTICS_PRICE = Statistics.builder()
            .min(new BigDecimal("120"))
            .avg(new BigDecimal("136.6666666666666666666666666666667"))
            .max(new BigDecimal("160"))
            .build();

    Statistics STATISTICS_MILEAGE = Statistics.builder()
            .min(new BigDecimal("1500"))
            .avg(new BigDecimal("1900.0"))
            .max(new BigDecimal("2500"))
            .build();

}
