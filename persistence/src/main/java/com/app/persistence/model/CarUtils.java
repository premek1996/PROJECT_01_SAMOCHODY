package com.app.persistence.model;

import java.util.Comparator;

public interface CarUtils {
    Comparator<Car> compareByModelAsc = Comparator.comparing(Car::getModel);
    Comparator<Car> compareByModelDesc = Comparator.comparing(Car::getModel, Comparator.reverseOrder());
    Comparator<Car> compareByColorAsc = Comparator.comparing(Car::getColor);
    Comparator<Car> compareByColorDesc = Comparator.comparing(Car::getColor, Comparator.reverseOrder());
    Comparator<Car> compareByPriceAsc = Comparator.comparing(Car::getPrice);
    Comparator<Car> compareByPriceDesc = Comparator.comparing(Car::getPrice, Comparator.reverseOrder());
    Comparator<Car> compareByMileageAsc = Comparator.comparing(Car::getMileage);
    Comparator<Car> compareByMileageDesc = Comparator.comparing(Car::getMileage, Comparator.reverseOrder());
}
