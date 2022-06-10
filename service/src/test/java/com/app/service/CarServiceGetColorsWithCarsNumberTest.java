package com.app.service;

import com.app.persistence.model.Color;
import com.app.service.extensions.CarServiceExtension;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceGetColorsWithCarsNumberTest {

    private final CarService carService;

    @Test
    @DisplayName("When cars are available")
    void test1() {
        Map<Color, Long> expectedColorsWithCarsNumber = Map.of(Color.RED, 1L,
                Color.GREEN, 1L,
                Color.BLUE, 1L);
        assertThat(carService.getColorsWithCarsNumber())
                .containsExactlyInAnyOrderEntriesOf(expectedColorsWithCarsNumber);
    }

}
