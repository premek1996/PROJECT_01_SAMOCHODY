package com.app.service.extensions;

import com.app.service.CarService;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CarServiceExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CarService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        var filename = "C:\\Users\\jambop\\Desktop\\szkolenie\\projects\\PROJECT_01_SAMOCHODY\\service\\src\\test\\resources\\cars-test.json";
        return new CarService(filename);
    }

}

// TODO
// testy parametryzowane mozesz uzyc tam gdzie masz jako argument
// enum, napis ktory bedzie przyjmowal kilka wartosci, mileage
// bo chodzi o to ze jak masznp mileaga to robisz zestaw danych
// np [-1, 0, 1000, 2000]


// Wybierz dowolna klase z testami i z rob testy dynamiczne

// Wybioerz metode ktora przyjmuje np int ale moze byc tez string
// czy enum i pusc ja 1000 razy losujac rozne wartosci i sprawdz
// czy zawsze dziala - repeatable test

//