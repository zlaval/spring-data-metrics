package com.zlrx.dbperftest.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.Optional;

@NotInject
@RequiredArgsConstructor
public class CarRepositoryImpl implements VehicleRepository<Car> {

    private final JdbcClient jdbcClient;

    @Override
    public Optional<Car> findItById(Long id) {
        var res = jdbcClient.sql("SELECT * FROM cars where id = %d".formatted(id))
                .query(Car.class)
                .optional();
        return res;
    }

    @Override
    public boolean supported(VehicleType type) {
        return type == VehicleType.CAR;
    }
}
