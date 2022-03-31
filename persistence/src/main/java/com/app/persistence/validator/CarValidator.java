package com.app.persistence.validator;

import com.app.persistence.model.Car;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarValidator implements Validator<Car> {

    private final Map<String, String> errors;
    private static final String OBJECT_IS_NULL = "object is null";

    public CarValidator() {
        this.errors = new HashMap<>();
    }

    @Override
    public Map<String, String> validate(Car car) {
        if (car == null) {
            errors.put("car", OBJECT_IS_NULL);
            return errors;
        }
        validateModel(car.getModel());
        validateMileage(car.getMileage());
        validatePrice(car.getPrice());
        validateComponents(car.getComponents());
        return errors;
    }

    private void validateModel(String model) {
        if (model == null) {
            errors.put("model", OBJECT_IS_NULL);
        } else if (containsNotOnlyWhitespacesOrUpperCaseCharacters(model)) {
            errors.put("model", "doesn't match regex");
        }
    }

    private static boolean containsNotOnlyWhitespacesOrUpperCaseCharacters(String word) {
        return !word.matches("[A-Z\\s]+");
    }

    private static boolean containNotOnlyWhitespacesOrUpperCaseCharacters(List<String> words) {
        return words.stream()
                .anyMatch(CarValidator::containsNotOnlyWhitespacesOrUpperCaseCharacters);
    }

    private void validateMileage(int mileage) {
        if (mileage < 0) {
            errors.put("mileage", "has to be >= 0");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null) {
            errors.put("price", OBJECT_IS_NULL);
        } else if (price.compareTo(BigDecimal.ZERO) < 0) {
            errors.put("price", "has to be >= 0");
        }
    }

    private void validateComponents(List<String> components) {
        if (components == null) {
            errors.put("components", OBJECT_IS_NULL);
        } else if (containNotOnlyWhitespacesOrUpperCaseCharacters(components)) {
            errors.put("components", "doesn't match regex");
        }
    }

}
