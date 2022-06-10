package com.app.service;

import com.app.persistence.model.Car;
import com.app.service.exception.CarServiceException;
import com.app.service.extensions.CarServiceExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

import static com.app.service.utils.CarsUtils.BMW;
import static com.app.service.utils.CarsUtils.MERCEDES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceFindCarsWithPriceInRangeTest {

    private final CarService carService;

    @Test
    @DisplayName("When BigDecimal object is null")
    void test1() {
        assertThatThrownBy(() -> carService.findCarsWithPriceInRange(null, BigDecimal.ONE))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("BigDecimal object is null");
    }

    @Test
    @DisplayName("When given price is negative")
    void test2() {
        assertThatThrownBy(() -> carService.findCarsWithPriceInRange(BigDecimal.valueOf(-1), BigDecimal.ONE))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Given price must be positive");
    }

    @Test
    @DisplayName("When minPrice is greater than maxPrice")
    void test3() {
        assertThatThrownBy(() -> carService.findCarsWithPriceInRange(BigDecimal.valueOf(10), BigDecimal.valueOf(5)))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("minPrice cannot be greater than maxPrice");
    }

    @Test
    @DisplayName("When there are no cars with price in given range")
    void test4() {
        assertThat(carService.findCarsWithPriceInRange(BigDecimal.valueOf(200), BigDecimal.valueOf(250)))
                .isEmpty();
    }

    @Test
    @DisplayName("When there are cars with price in given range")
    void test5() {
        List<Car> expectedCars = List.of(BMW, MERCEDES);
        assertThat(carService.findCarsWithPriceInRange(BigDecimal.valueOf(100), BigDecimal.valueOf(140)))
                .containsExactlyElementsOf(expectedCars);
    }

}
