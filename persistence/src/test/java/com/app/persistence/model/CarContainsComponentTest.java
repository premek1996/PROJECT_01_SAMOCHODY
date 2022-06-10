package com.app.persistence.model;

import com.app.persistence.model.exception.CarException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.persistence.utils.CarsUtils.MAZDA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarContainsComponentTest {

    @Test
    @DisplayName("When component is null")
    void test1() {
        assertThatThrownBy(() -> MAZDA.containsComponent(null))
                .isInstanceOf(CarException.class)
                .hasMessage("component is null");
    }

    @Test
    @DisplayName("When car does not contain component")
    void test2() {
        assertThat(MAZDA.containsComponent("ABS")).isFalse();
    }

    @Test
    @DisplayName("When car contains component")
    void test3() {
        assertThat(MAZDA.containsComponent("AIR CONDITIONING")).isTrue();
    }

}
