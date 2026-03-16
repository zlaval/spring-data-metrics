package com.zlrx.dbperftest.vehicle;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Getter
@Setter
public abstract class Vehicle {

    @Id
    protected Long id;

    @Transient
    public abstract VehicleType getType();

}
