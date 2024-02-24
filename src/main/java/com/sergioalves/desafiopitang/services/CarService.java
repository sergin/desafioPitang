package com.sergioalves.desafiopitang.services;

import com.sergioalves.desafiopitang.domain.category.Car;
import com.sergioalves.desafiopitang.domain.category.CarDTO;
import com.sergioalves.desafiopitang.domain.category.exceptions.CarNotFoundException;
import com.sergioalves.desafiopitang.repositories.CarRepository;
import com.sergioalves.desafiopitang.services.aws.AwsSnsService;
import com.sergioalves.desafiopitang.services.aws.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;
    private final AwsSnsService snsService;

    public Car insert(CarDTO categoryData){
        Car newCar = new Car(categoryData);

        this.repository.save(newCar);

        this.snsService.publish(new MessageDTO(newCar.toString()));

        return newCar;
    }

    public Car update(String id, CarDTO carData){
        Car car = this.repository.findById(id)
                .orElseThrow(CarNotFoundException::new);

        this.snsService.publish(new MessageDTO(car.toString()));

        this.repository.save(car);

        return car;
    }

    public void delete(String id){
        Car car = this.repository.findById(id)
                .orElseThrow(CarNotFoundException::new);

        this.repository.delete(car);
        this.snsService.publish(new MessageDTO(car.deleteToString()));
    }

    public List<Car> getAll(){
        return this.repository.findAll();
    }

    public Optional<Car> getById(String id){
        return this.repository.findById(id);
    }

}
