package com.app.service;

import com.app.service.exception.CarServiceException;
import com.app.service.extensions.CarServiceExtension;
import com.app.service.type.StatisticsType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.app.service.utils.CarsUtils.STATISTICS_MILEAGE;
import static com.app.service.utils.CarsUtils.STATISTICS_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceGetStatisticsTest {

    private final CarService carService;

    @Test
    @DisplayName("When StatisticsType object is null")
    void test1() {
        assertThatThrownBy(() -> carService.getStatistics(null))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("StatisticsType object is null");
    }

    @Test
    @DisplayName("When StatisticsType is mileage")
    void test2() {
        assertThat(carService.getStatistics(StatisticsType.MILEAGE))
                .isEqualTo(STATISTICS_MILEAGE);
    }

    @Test
    @DisplayName("When StatisticsType is price")
    void test3() {
        assertThat(carService.getStatistics(StatisticsType.PRICE))
                .isEqualTo(STATISTICS_PRICE);
    }

    @ParameterizedTest
    @EnumSource(StatisticsType.class)
    void test4(StatisticsType statisticsType) {
        switch (statisticsType) {
            case PRICE -> assertThat(carService.getStatistics(StatisticsType.PRICE))
                    .isEqualTo(STATISTICS_PRICE);
            case MILEAGE -> assertThat(carService.getStatistics(StatisticsType.MILEAGE))
                    .isEqualTo(STATISTICS_MILEAGE);
        }
    }

}
