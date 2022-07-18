package com.app.persistence.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.app.persistence.utils.CarsUtils.MAZDA;
import static org.assertj.core.api.Assertions.assertThat;

public class CarContainsComponentTest {

    @Test
    @DisplayName("When car does not contain component")
    void test1() {
        assertThat(MAZDA.containsComponent("ABS")).isFalse();
    }

    static Stream<String> unavailableComponentsProvider() {
        return Stream.of("ALLOY WHEELS", "ABS");
    }

    @ParameterizedTest
    @DisplayName("When car does not contain component")
    @MethodSource("unavailableComponentsProvider")
    void test2(String component) {
        assertThat(MAZDA.containsComponent(component)).isFalse();
    }

    @Test
    @DisplayName("When car contains component")
    void test3() {
        assertThat(MAZDA.containsComponent("AIR CONDITIONING")).isTrue();
    }

    static Stream<String> availableComponentsProvider() {
        return Stream.of("BLUETOOTH", "AIR CONDITIONING");
    }

    @ParameterizedTest
    @DisplayName("When car contains component")
    @MethodSource("availableComponentsProvider")
    void test4(String component) {
        assertThat(MAZDA.containsComponent(component)).isTrue();
    }

}
