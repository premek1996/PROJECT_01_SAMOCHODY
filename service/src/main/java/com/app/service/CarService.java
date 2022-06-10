package com.app.service;

import com.app.persistence.converter.CarsJsonConverter;
import com.app.persistence.model.Car;
import com.app.persistence.model.Color;
import com.app.persistence.validator.CarValidator;
import com.app.persistence.validator.Validator;
import com.app.service.exception.CarServiceException;
import com.app.service.type.Sort;
import com.app.service.type.Statistics;
import com.app.service.type.StatisticsType;
import org.eclipse.collections.impl.collector.Collectors2;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.app.persistence.model.CarUtils.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class CarService {

    private final List<Car> cars;

    public CarService(String filename) {
        this.cars = init(filename);
    }

    private static List<Car> init(String filename) {
        CarValidator carValidator = new CarValidator();
        return new CarsJsonConverter(filename)
                .fromJson()
                .orElseThrow(() -> new CarServiceException("Cannot read data from json %s%n".formatted(filename)))
                .stream()
                .peek(car -> Validator.validate(car, carValidator))
                .toList();
    }

    /*
        Metoda, która zwraca nową kolekcję elementów Car posortowaną
        według podanego jako argument metody kryterium. Metoda powinna
        mieć możliwość sortowania po nazwie modelu, kolorze, cenie oraz
        przebiegu. Dodatkowo należy określić czy sortowanie ma odbywać
        się malejąco czy rosnąco.
     */
    public List<Car> sort(Sort sort, boolean descending) {
        if (sort == null) {
            throw new CarServiceException("Sort object is null");
        }
        List<Car> sortedCars = switch (sort) {
            case COLOR -> cars.stream().sorted(descending ? compareByColorDesc : compareByColorAsc).toList();
            case MODEL -> cars.stream().sorted(descending ? compareByModelDesc : compareByModelAsc).toList();
            case MILEAGE -> cars.stream().sorted(descending ? compareByMileageDesc : compareByMileageAsc).toList();
            case PRICE -> cars.stream().sorted(descending ? compareByPriceDesc : compareByPriceAsc).toList();
        };
        return sortedCars;
    }

    /*
        Metoda zwraca kolekcję elementów typu Car, które posiadają
        przebieg o wartości większej niż wartość podana jako argument
        metody.
     */
    public List<Car> findCarsWithMileageGreaterThan(int mileage) {
        if (mileage < 0) {
            throw new CarServiceException("Mileage must be positive!");
        }
        return cars.stream()
                .filter(car -> car.hasMileageGreaterThan(mileage))
                .toList();
    }

    /*
        Metoda zwraca mapę, której kluczem jest kolor, natomiast
        wartością ilość samochodów, które posiadają taki kolor. Mapa
        powinna być posortowana malejąco po wartościach.
     */
    public Map<Color, Long> getColorsWithCarsNumber() {
        return cars.stream()
                .collect(groupingBy(Car::getColor, counting()))
                .entrySet()
                .stream()
                .sorted(comparingLong(Map.Entry::getValue))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum, LinkedHashMap::new));
    }

    /*
        Metoda zwraca mapę, której kluczem jest nazwa modelu samochodu,
        natomiast wartością obiekt klasy Car, który reprezentuje
        najdroższy samochód o tej nazwie modelu. Mapa powinna być
        posortowana kluczami malejąco.
     */
    public Map<String, List<Car>> getModelsWithMostExpensiveCars() {
        return cars.stream()
                .collect(groupingBy(Car::getModel, collectingAndThen(mapping(Function.identity(), toList()), CarService::getMostExpensiveCars)));
    }

    private static List<Car> getMostExpensiveCars(List<Car> cars) {
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getPrice))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList());
    }

    /*
        Metoda wypisuje statystykę samochodów w zestawieniu. W
        statystyce powinny znajdować się wartość średnia, wartość
        najmniejsza, wartość największa dla pól opisujących cenę oraz
        przebieg samochodów.
     */
    public Statistics getStatistics(StatisticsType statisticsType) {
        if (statisticsType == null) {
            throw new CarServiceException("StatisticsType object is null");
        }

        return switch (statisticsType) {
            case MILEAGE -> Statistics.toStatistics(cars
                    .stream()
                    .collect(Collectors.summarizingInt(Car::getMileage))
            );
            case PRICE -> Statistics.toStatistics(cars
                    .stream()
                    .collect(Collectors2.summarizingBigDecimal(Car::getPrice))
            );
        };
    }

    /*
        Metoda zwraca samochód, którego cena jest największa. W
        przypadku kiedy więcej niż jeden samochód posiada największą
        cenę należy zwrócić kolekcję tych samochodów.
     */
    public List<Car> getMostExpensiveCars() {
        return cars.stream()
                .collect(groupingBy(Car::getPrice))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElse(Collections.emptyList());
    }

    /*
        Metoda zwraca kolekcję samochodów, w której każdy samochód
        posiada posortowaną alfabetycznie kolekcję komponentów.
     */
    public List<Car> getCarsWithSortedComponents() {
        return cars.stream()
                .map(Car::withSortedComponents)
                .toList();
    }

    /*
        Metoda zwraca mapę, której kluczem jest nazwa komponentu,
        natomiast wartością jest kolekcja samochodów, które posiadają
        ten komponent. Pary w mapie powinny być posortowane malejąco po
        liczbie elementów w kolekcji reprezentującej wartość pary.
     */
    public Map<String, List<Car>> getComponentsWithCars() {
        return getComponents().stream()
                .collect(toMap(Function.identity(), this::getCarsWhichContainComponent))
                .entrySet()
                .stream()
                .sorted(comparingInt(entry -> entry.getValue().size()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }

    private List<String> getComponents() {
        return cars.stream()
                .map(Car::getComponents)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    private List<Car> getCarsWhichContainComponent(String component) {
        return cars.stream()
                .filter(car -> car.containsComponent(component))
                .toList();
    }

    /*
        Metoda zwraca kolekcję samochodów, których cena znajduje się w
        przedziale cenowym <a, b>. Wartości a oraz b przekazywane są
        jako argument metody. Kolekcja powinna być posortowana
        alfabetycznie według nazw samochodów.
     */
    public List<Car> findCarsWithPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new CarServiceException("BigDecimal object is null");
        }
        if (minPrice.compareTo(BigDecimal.ZERO) < 0 || maxPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new CarServiceException("Given price must be positive");
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new CarServiceException("minPrice cannot be greater than maxPrice");
        }
        return cars.stream()
                .filter(car -> car.hasPriceInRange(minPrice, maxPrice))
                .sorted(comparing(Car::getModel))
                .toList();
    }

}
