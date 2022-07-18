package com.app.persistence.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.persistence.utils.CarsUtils.MAZDA;
import static org.assertj.core.api.Assertions.assertThat;

public class CarHasMileageGreaterThanTest {

    @Test
    @DisplayName("When car does not have mileage greater than provided value")
    void test1() {
        assertThat(MAZDA.hasMileageGreaterThan(10000)).isFalse();
    }

    @Test
    @DisplayName("When car has mileage greater than provided value")
    void test2() {
        assertThat(MAZDA.hasMileageGreaterThan(2400)).isTrue();
    }

    @Test
    @DisplayName("When car has mileage equal to provided value")
    void test3() {
        assertThat(MAZDA.hasMileageGreaterThan(MAZDA.getMileage())).isFalse();
    }


}
