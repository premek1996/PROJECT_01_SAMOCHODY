package com.app.persistence.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.persistence.utils.CarsUtils.MAZDA;
import static com.app.persistence.utils.CarsUtils.MAZDA_WITH_SORTED_COMPONENTS;
import static org.assertj.core.api.Assertions.assertThat;

public class CarWithSortedComponentsTest {

    @Test
    @DisplayName("When car does not have sorted components")
    void test1() {
        assertThat(MAZDA.withSortedComponents())
                .isEqualTo(MAZDA_WITH_SORTED_COMPONENTS);
    }

}
