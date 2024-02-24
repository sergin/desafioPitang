package com.sergioalves.desafiopitang.domain.category;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "car")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Car {
    @Id
    private String id;
    private String year;
    private String licensePlate;
    private String model;
    private String color;

    public Car(CarDTO carDTO){
        this.year = carDTO.year();
        this.licensePlate = carDTO.licensePlate();
        this.model = carDTO.model();
        this.color = carDTO.color();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("year", year);
        json.put("licensePlate", licensePlate);
        json.put("model", model);
        json.put("color", color);

        return json.toString();
    }

    public String deleteToString(){
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("type", "delete-car");

        return json.toString();
    }
}
