package com.app.persistence.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.app.persistence.utils.CarsUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CarValidatorValidateTest {

    private CarValidator carValidator;

    @BeforeEach
    void beforeEachTest() {
        carValidator = new CarValidator();
    }

    @Test
    @DisplayName("When car is null")
    void test1() {
        Map<String, String> expectedErrors = Map.of("car", "object is null");
        Map<String, String> errors = carValidator.validate(null);
        assertThat(errors).containsExactlyEntriesOf(expectedErrors);
    }

    @Test
    @DisplayName("When model is null")
    void test2() {
        Map<String, String> expectedErrors = Map.of("model", "object is null");
        Map<String, String> errors = carValidator.validate(MAZDA_WITH_MODEL_NULL);
        assertThat(errors).containsExactlyEntriesOf(expectedErrors);
    }

    @Test
    @DisplayName("When model is wrong")
    void test3() {
        Map<String, String> expectedErrors = Map.of("model", "doesn't match regex");
        Map<String, String> errors = carValidator.validate(MAZDA_WITH_WRONG_MODEL);
        assertThat(errors).containsExactlyEntriesOf(expectedErrors);
    }

    @Test
    @DisplayName("When mileage is wrong")
    void test4() {
        Map<String, String> expectedErrors = Map.of("mileage", "has to be >= 0");
        Map<String, String> errors = carValidator.validate(MAZDA_WITH_WRONG_MILEAGE);
        assertThat(errors).containsExactlyEntriesOf(expectedErrors);
    }

    @Test
    @DisplayName("When price is null")
    void test5() {
        Map<String, String> expectedErrors = Map.of("price", "object is null");
        Map<String, String> errors = carValidator.validate(MAZDA_WITH_PRICE_NULL);
        assertThat(errors).containsExactlyEntriesOf(expectedErrors);
    }

    @Test
    @DisplayName("When price is wrong")
    void test6() {
        Map<String, String> expectedErrors = Map.of("price", "has to be >= 0");
        Map<String, String> errors = carValidator.validate(MAZDA_WITH_WRONG_PRICE);
        assertThat(errors).containsExactlyEntriesOf(expectedErrors);
    }

    @Test
    @DisplayName("When components are null")
    void test7() {
        Map<String, String> expectedErrors = Map.of("components", "object is null");
        Map<String, String> errors = carValidator.validate(MAZDA_WITH_COMPONENTS_NULL);
        assertThat(errors).containsExactlyEntriesOf(expectedErrors);
    }

    @Test
    @DisplayName("When components are wrong")
    void test8() {
        Map<String, String> expectedErrors = Map.of("components", "doesn't match regex");
        Map<String, String> errors = carValidator.validate(MAZDA_WITH_WRONG_COMPONENTS);
        assertThat(errors).containsExactlyEntriesOf(expectedErrors);
    }

}
