package com.zlrx.dbperftest.vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship extends Vehicle {

    private String shipName;

    @Override
    public VehicleType getType() {
        return VehicleType.SHIP;
    }
}