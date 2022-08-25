package com.ldt.BusService.model;

import com.ldt.BusService.util.BusReservationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@Setter
public class BusDaoFilter implements Serializable {

    private String name;
    private String type;
    private String number;
    private int offset;
    private int limit;
    private int count;

    @Enumerated(EnumType.STRING)
    private BusReservationStatus busReservationStatus;
}
