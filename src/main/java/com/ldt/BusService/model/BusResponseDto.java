package com.ldt.BusService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ldt.BusService.entity.Bus;
import com.ldt.BusService.util.BusReservationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BusResponseDto implements Serializable{

    private int busId;
    private String busName;
    private String busType;
    private String busNumber;
    private BusReservationStatus busReservationStatus;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDateTime registeredAt=LocalDateTime.now();
    private int status;

}
