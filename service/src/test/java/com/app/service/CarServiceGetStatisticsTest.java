package com.app.service;

import com.app.service.exception.CarServiceException;
import com.app.service.extensions.CarServiceExtension;
import com.app.service.type.Statistics;
import com.app.service.type.StatisticsType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

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
        Statistics expectedStatistics = new Statistics(new BigDecimal("1500"), new BigDecimal("1900.0"), new BigDecimal("2500"));
        assertThat(carService.getStatistics(StatisticsType.MILEAGE))
                .isEqualTo(expectedStatistics);
    }

    @Test
    @DisplayName("When StatisticsType is price")
    void test3() {
        Statistics expectedStatistics = new Statistics(new BigDecimal("120"), new BigDecimal("136.6666666666666666666666666666667"), new BigDecimal("160"));
        assertThat(carService.getStatistics(StatisticsType.PRICE))
                .isEqualTo(expectedStatistics);
    }

}
