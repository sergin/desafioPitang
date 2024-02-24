package com.sergioalves.desafiopitang.repositories;

import com.sergioalves.desafiopitang.domain.category.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
}
