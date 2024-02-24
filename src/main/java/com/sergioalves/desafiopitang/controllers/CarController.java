package com.sergioalves.desafiopitang.controllers;

import com.sergioalves.desafiopitang.domain.category.Car;
import com.sergioalves.desafiopitang.domain.category.CarDTO;
import com.sergioalves.desafiopitang.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {
    private final CarService service;

    @PostMapping
    public ResponseEntity<Car> insert(@RequestBody CarDTO carData){
        Car newCar = this.service.insert(carData);
        return ResponseEntity.ok().body(newCar);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = this.service.getAll();
        return ResponseEntity.ok().body(cars);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable("id") String id, @RequestBody CarDTO carData){
        Car updatedCar = this.service.update(id, carData);
        return ResponseEntity.ok().body(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> delete(@PathVariable("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
