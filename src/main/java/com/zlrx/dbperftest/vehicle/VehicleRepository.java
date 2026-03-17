package com.zlrx.dbperftest.vehicle;

import com.zlrx.dbperftest.config.ExcludeFromMetrics;

import java.util.Optional;

public interface VehicleRepository<T extends Vehicle> {

    Optional<T> findItById(Long id);

    @ExcludeFromMetrics
    boolean supported(VehicleType type);
}
