package com.app.persistence.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.app.persistence.utils.CarsUtils.MAZDA;
import static org.assertj.core.api.Assertions.assertThat;

public class CarHasPriceInRangeTest {

    @Test
    @DisplayName("When car does not have price in given range")
    void test1() {
        assertThat(MAZDA.hasPriceInRange(BigDecimal.valueOf(170), BigDecimal.valueOf(180)))
                .isFalse();
    }

    @Test
    @DisplayName("When car has price in given range")
    void test2() {
        assertThat(MAZDA.hasPriceInRange(BigDecimal.valueOf(160), BigDecimal.valueOf(180)))
                .isTrue();
    }

}
