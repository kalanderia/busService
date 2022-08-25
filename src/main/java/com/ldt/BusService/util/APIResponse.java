package com.ldt.BusService.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse{

    private String status;
    private String statusCode;
    private String message;
    private Object object;
    private int count;
    public APIResponse (String status,String statusCode,String message,Object object){
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.object = object;
    }
    public APIResponse(String status,String statusCode,String message, int count,Object object){
        super();
        this.status=status;
        this.statusCode=statusCode;
        this.message=message;
        this.object=object;
        this.count = count;
    }


}
