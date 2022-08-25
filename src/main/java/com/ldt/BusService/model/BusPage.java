package com.ldt.BusService.model;

import lombok.Data;

import java.util.Map;

@Data
public class BusPage {

    private int offSet=0;
    private int PageSize=5;
    private Map<String,String> filters;

}

