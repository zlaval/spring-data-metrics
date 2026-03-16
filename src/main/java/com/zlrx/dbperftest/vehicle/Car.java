package com.zlrx.dbperftest.vehicle;

import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table("cars")
public class Car extends Vehicle{

    private String carName;

    @Override
    @Transient
    public VehicleType getType() {
        return VehicleType.CAR;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
}
