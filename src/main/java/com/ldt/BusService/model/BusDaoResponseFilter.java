package com.ldt.BusService.model;

import com.ldt.BusService.util.BusReservationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Getter
@Setter
public class BusDaoResponseFilter implements Serializable {
    private String busName;
    private String busType;
    private String busNumber;

    @Enumerated(EnumType.STRING)
    private BusReservationStatus busReservationStatus;
}
