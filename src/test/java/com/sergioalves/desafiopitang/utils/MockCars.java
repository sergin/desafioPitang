package com.sergioalves.desafiopitang.utils;

import com.sergioalves.desafiopitang.domain.category.Car;
import com.sergioalves.desafiopitang.domain.category.CarDTO;

import java.util.ArrayList;
import java.util.List;

public class MockCars {
    public static String ID_CAR = "1";
    public static String YEAR = "2000";
    public static String LICENSEPLATE = "PDV-1234";
    public static String MODEL = "Chevrolet";
    public static String COLOR = "Azul";

    public static CarDTO mockCarDTO(){
        return new CarDTO(YEAR, LICENSEPLATE, MODEL, COLOR);
    }

    public static Car mockCarEntity(){
        return new Car(mockCarDTO());
    }
    public static List<Car> mockCarList(){
        Car car = mockCarEntity();
        List<Car> listCategories = new ArrayList<>();
        listCategories.add(car);
        return listCategories;
    };
}
