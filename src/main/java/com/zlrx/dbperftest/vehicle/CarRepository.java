package com.zlrx.dbperftest.vehicle;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

@Primary
public interface CarRepository extends CrudRepository<Car, Long>, VehicleRepository<Car>,VehicleRepositoryProxy<Car> {
}
