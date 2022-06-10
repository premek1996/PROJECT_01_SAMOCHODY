package com.app.persistence.utils;

import com.app.persistence.model.Car;
import com.app.persistence.model.Color;

import java.math.BigDecimal;
import java.util.List;

public interface CarsUtils {

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

    Car MAZDA_WITH_MODEL_NULL = Car
            .builder()
            .price(BigDecimal.valueOf(160))
            .color(Color.RED)
            .mileage(2500)
            .components(List.of("BLUETOOTH", "AIR CONDITIONING"))
            .build();

    Car MAZDA_WITH_WRONG_MODEL = Car
            .builder()
            .model("MAZafd34DA")
            .price(BigDecimal.valueOf(160))
            .color(Color.RED)
            .mileage(2500)
            .components(List.of("BLUETOOTH", "AIR CONDITIONING"))
            .build();

    Car MAZDA_WITH_WRONG_MILEAGE = Car
            .builder()
            .model("MAZDA")
            .price(BigDecimal.valueOf(160))
            .color(Color.RED)
            .mileage(-50)
            .components(List.of("BLUETOOTH", "AIR CONDITIONING"))
            .build();

    Car MAZDA_WITH_PRICE_NULL = Car
            .builder()
            .model("MAZDA")
            .color(Color.RED)
            .mileage(2500)
            .components(List.of("BLUETOOTH", "AIR CONDITIONING"))
            .build();

    Car MAZDA_WITH_WRONG_PRICE = Car
            .builder()
            .model("MAZDA")
            .price(BigDecimal.valueOf(-10))
            .color(Color.RED)
            .mileage(2500)
            .components(List.of("BLUETOOTH", "AIR CONDITIONING"))
            .build();

    Car MAZDA_WITH_COMPONENTS_NULL = Car
            .builder()
            .model("MAZDA")
            .price(BigDecimal.valueOf(160))
            .color(Color.RED)
            .mileage(2500)
            .build();

    Car MAZDA_WITH_WRONG_COMPONENTS = Car
            .builder()
            .model("MAZDA")
            .price(BigDecimal.valueOf(160))
            .color(Color.RED)
            .mileage(2500)
            .components(List.of("BLUETOt36grOTH", "AIR CONy5 y5DIT 5yIONING"))
            .build();

}
