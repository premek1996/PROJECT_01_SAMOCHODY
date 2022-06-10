package com.app.service;

import com.app.persistence.model.Car;
import com.app.service.exception.CarServiceException;
import com.app.service.extensions.CarServiceExtension;
import com.app.service.type.Sort;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.app.service.utils.CarsUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@ExtendWith(CarServiceExtension.class)
public class CarServiceSortTest {

    private final CarService carService;

    @Test
    @DisplayName("When Sort object is null")
    void test1() {
        assertThatThrownBy(() -> carService.sort(null, true))
                .isInstanceOf(CarServiceException.class)
                .hasMessage("Sort object is null");
    }

    @Test
    @DisplayName("When cars collection is sorted by color ascending")
    void test2() {
        List<Car> expectedSortedCars = List.of(MAZDA, MERCEDES, BMW);
        List<Car> sortedCars = carService.sort(Sort.COLOR, false);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by color descending")
    void test3() {
        List<Car> expectedSortedCars = List.of(BMW, MERCEDES, MAZDA);
        List<Car> sortedCars = carService.sort(Sort.COLOR, true);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by price ascending")
    void test4() {
        List<Car> expectedSortedCars = List.of(BMW, MERCEDES, MAZDA);
        List<Car> sortedCars = carService.sort(Sort.PRICE, false);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by price descending")
    void test5() {
        List<Car> expectedSortedCars = List.of(MAZDA, MERCEDES, BMW);
        List<Car> sortedCars = carService.sort(Sort.PRICE, true);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by mileage ascending")
    void test6() {
        List<Car> expectedSortedCars = List.of(BMW, MERCEDES, MAZDA);
        List<Car> sortedCars = carService.sort(Sort.MILEAGE, false);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by mileage descending")
    void test7() {
        List<Car> expectedSortedCars = List.of(MAZDA, MERCEDES, BMW);
        List<Car> sortedCars = carService.sort(Sort.MILEAGE, true);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by model ascending")
    void test8() {
        List<Car> expectedSortedCars = List.of(BMW, MAZDA, MERCEDES);
        List<Car> sortedCars = carService.sort(Sort.MODEL, false);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

    @Test
    @DisplayName("When cars collection is sorted by model descending")
    void test9() {
        List<Car> expectedSortedCars = List.of(MERCEDES, MAZDA, BMW);
        List<Car> sortedCars = carService.sort(Sort.MODEL, true);
        assertThat(sortedCars).containsExactlyElementsOf(expectedSortedCars);
    }

}
