package com.app.persistence.model;

import com.app.persistence.model.exception.CarException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.persistence.utils.CarsUtils.MAZDA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarHasMileageGreaterThanTest {

    @Test
    @DisplayName("When mileage value is not positive")
    void test1() {
        assertThatThrownBy(() -> MAZDA.hasMileageGreaterThan(-1))
                .isInstanceOf(CarException.class)
                .hasMessage("Mileage must be positive!");
    }

    @Test
    @DisplayName("When car does not have mileage greater than provided value")
    void test2() {
        assertThat(MAZDA.hasMileageGreaterThan(10000)).isFalse();
    }

    @Test
    @DisplayName("When car has mileage greater than provided value")
    void test4() {
        assertThat(MAZDA.hasMileageGreaterThan(2400)).isTrue();
    }

    @Test
    @DisplayName("When car has mileage equal to provided value")
    void test5() {
        assertThat(MAZDA.hasMileageGreaterThan(MAZDA.getMileage())).isFalse();
    }


}
