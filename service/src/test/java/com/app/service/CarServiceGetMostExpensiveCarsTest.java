package com.app.service;

import com.app.persistence.model.Car;
import com.app.service.extensions.CarServiceExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.app.service.utils.CarsUtils.MAZDA;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceGetMostExpensiveCarsTest {

    private final CarService carService;

    @Test
    @DisplayName("When cars are available")
    void test1() {
        List<Car> expectedMostExpensiveCars = List.of(MAZDA);
        assertThat(carService.getMostExpensiveCars())
                .containsExactlyElementsOf(expectedMostExpensiveCars);
    }

}
