package com.app.service;

import com.app.persistence.model.Car;
import com.app.service.extensions.CarServiceExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static com.app.service.utils.CarsUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceGetComponentsWithCarsTest {

    private final CarService carService;

    @Test
    @DisplayName("When cars are available")
    void test1() {
        Map<String, List<Car>> componentsWithCars = Map.of("ABS", List.of(BMW, MERCEDES),
                "AIR CONDITIONING", List.of(MAZDA, MERCEDES),
                "ALLOY WHEELS", List.of(BMW),
                "BLUETOOTH", List.of(MAZDA, MERCEDES));
        assertThat(carService.getComponentsWithCars())
                .containsExactlyInAnyOrderEntriesOf(componentsWithCars);
    }

}
