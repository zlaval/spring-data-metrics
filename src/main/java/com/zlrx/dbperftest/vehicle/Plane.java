package com.zlrx.dbperftest.vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plane extends Vehicle {

    private String planeName;

    @Override
    public VehicleType getType() {
        return VehicleType.PLANE;
    }
}
