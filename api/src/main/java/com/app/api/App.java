package com.app.api;

import com.app.service.CarService;
import com.app.service.type.Sort;

import java.math.BigDecimal;

public class App {

    private static final String INPUT_FILE_PATH = "data\\cars.json";

    public static void main(String[] args) {
        CarService carService = new CarService(INPUT_FILE_PATH);
        System.out.println(carService.findCarsWithMileageGreaterThan(1600));
        System.out.println(carService.getColorsWithCarsNumber());
        System.out.println(carService.getModelsWithMostExpensiveCars());
        System.out.println(carService.getMostExpensiveCars());
        System.out.println(carService.findCarsWithPriceInRange(new BigDecimal("140"), new BigDecimal("170")));
        System.out.println(carService.getComponentsWithCars());
        System.out.println(carService.getCarsWithSortedComponents());
        System.out.println(carService.sort(Sort.COLOR, false));
        System.out.println(carService.sort(Sort.MODEL, false));
        System.out.println(carService.sort(Sort.PRICE, false));
        System.out.println(carService.sort(Sort.MILEAGE, false));
    }

}

