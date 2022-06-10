package com.app.persistence.model;

import com.app.persistence.model.exception.CarException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.app.persistence.utils.CarsUtils.MAZDA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarHasPriceInRangeTest {

    @Test
    @DisplayName("When BigDecimal object is null")
    void test1() {
        assertThatThrownBy(() -> MAZDA.hasPriceInRange(null, BigDecimal.ONE))
                .isInstanceOf(CarException.class)
                .hasMessage("BigDecimal object is null");
    }

    @Test
    @DisplayName("When given price is not positive")
    void test2() {
        assertThatThrownBy(() -> MAZDA.hasPriceInRange(BigDecimal.valueOf(-1), BigDecimal.ONE))
                .isInstanceOf(CarException.class)
                .hasMessage("Given price must be positive");
    }

    @Test
    @DisplayName("When minPrice is greater than maxPrice")
    void test3() {
        assertThatThrownBy(() -> MAZDA.hasPriceInRange(BigDecimal.valueOf(10), BigDecimal.valueOf(2)))
                .isInstanceOf(CarException.class)
                .hasMessage("minPrice cannot be greater than maxPrice");
    }

    @Test
    @DisplayName("When car does not have price in given range")
    void test4() {
        assertThat(MAZDA.hasPriceInRange(BigDecimal.valueOf(170), BigDecimal.valueOf(180)))
                .isFalse();

    }

    @Test
    @DisplayName("When car has price in given range")
    void test5() {
        assertThat(MAZDA.hasPriceInRange(BigDecimal.valueOf(160), BigDecimal.valueOf(180)))
                .isTrue();

    }

}
