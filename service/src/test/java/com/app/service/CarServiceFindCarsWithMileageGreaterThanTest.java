package com.app.service;

import com.app.service.exception.CarServiceException;
import com.app.service.extensions.CarServiceExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.app.service.utils.CarsUtils.MAZDA;
import static com.app.service.utils.CarsUtils.MERCEDES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceFindCarsWithMileageGreaterThanTest {

    private final CarService carService;

    @Test
    @DisplayName("When mileage value is negative")
    void test1() {
        assertThatThrownBy(() -> carService.findCarsWithMileageGreaterThan(-1))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Mileage must be positive!");
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -50, -20, -10, -1})
    void test2(int mileage) {
        assertThatThrownBy(() -> carService.findCarsWithMileageGreaterThan(mileage))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Mileage must be positive!");
    }

    @Test
    @DisplayName("When there are no cars with mileage greater than provided value")
    void test3() {
        assertThat(carService.findCarsWithMileageGreaterThan(10000))
                .isEmpty();
    }

    @Test
    @DisplayName("When there are cars with mileage greater than provided value")
    void test4() {
        var expectedCars = List.of(MAZDA, MERCEDES);
        assertThat(carService.findCarsWithMileageGreaterThan(1500))
                .hasSize(2)
                .containsExactlyElementsOf(expectedCars);
    }

}
