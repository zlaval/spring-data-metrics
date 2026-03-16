package com.zlrx.dbperftest.vehicle;

import java.util.Optional;

public interface VehicleRepository<T extends Vehicle> {

    Optional<T> findItById(Long id);

    boolean supported(VehicleType type);
}
