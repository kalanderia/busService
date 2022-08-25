package com.ldt.BusService.model;

import com.ldt.BusService.util.BusReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BusRequestDto implements Serializable {

    private int busId;
    private String name;
    private String type;
    private String number;
    private BusReservationStatus busReservationStatus;
    private int status;

}
