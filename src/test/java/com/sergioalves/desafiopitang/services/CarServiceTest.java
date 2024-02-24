package com.sergioalves.desafiopitang.services;

import com.sergioalves.desafiopitang.domain.category.Car;
import com.sergioalves.desafiopitang.domain.category.CarDTO;
import com.sergioalves.desafiopitang.domain.category.exceptions.CarNotFoundException;
import com.sergioalves.desafiopitang.repositories.CarRepository;
import com.sergioalves.desafiopitang.services.aws.AwsSnsService;
import com.sergioalves.desafiopitang.services.aws.MessageDTO;
import com.sergioalves.desafiopitang.utils.MockCars;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository repository;
    @Mock
    private AwsSnsService snsService;

    @InjectMocks
    private CarService service;


    @Test
    void insertValidData() {
        final var input = new CarDTO("year", "licensePlate", "model", "color");
        final var car = new Car(input);
        given(repository.save(car)).willReturn(car);

        final var actual = service.insert(input);

        Assertions.assertEquals(car, actual);
        then(snsService).should().publish(new MessageDTO(car.toString()));
    }

    @Test
    void updateNonexistentCar() {
       assertThrows(CarNotFoundException.class,
           () -> service.update("id", null));
    }

    @Test
    @DisplayName("should delete Car when exists")
    void deleteSuccess() {
        final var car = MockCars.mockCarEntity();
        given(repository.findById(MockCars.ID_CAR)).willReturn(Optional.of(car));

        this.service.delete(MockCars.ID_CAR);

        then(repository).should().delete(car);
    }

    @Test
    @DisplayName("should throw exception when Car not exists")
    void deleteError() {
        given(repository.findById(MockCars.ID_CAR)).willReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> {
            this.service.delete(MockCars.ID_CAR);
        });
    }

    @Test
    @DisplayName("should return a Car List on getAll")
    void getAllSuccess() {
        given(repository.findAll()).willReturn(MockCars.mockCarList());

        final var result = this.service.getAll();

        assertNotNull(result);
        Assertions.assertEquals(result.get(0).getYear(), MockCars.YEAR);
        Assertions.assertEquals(result.get(0).getLicensePlate(), MockCars.LICENSEPLATE);
        Assertions.assertEquals(result.get(0).getModel(), MockCars.MODEL);
        Assertions.assertEquals(result.get(0).getColor(), MockCars.COLOR);
    }

    @Test
    void getById() {
    }
}