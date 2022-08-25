package com.ldt.BusService.controller;

import com.ldt.BusService.criteria.BusCriteria;
import com.ldt.BusService.entity.Bus;
import com.ldt.BusService.model.BusDaoFilter;
import com.ldt.BusService.model.BusPage;
import com.ldt.BusService.model.BusRequestDto;
import com.ldt.BusService.model.BusResponseDto;
import com.ldt.BusService.criteria.BusCriteria;
import com.ldt.BusService.service.BusService;
import com.ldt.BusService.util.APIResponse;
import com.sun.xml.internal.ws.api.message.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class BusAPI{

    @Autowired
    private BusService busService;
    @Autowired
    private BusCriteria busImp;

    @RequestMapping(value="/bus/create", method=RequestMethod.POST)
    public ResponseEntity<String> create (@Valid @RequestBody BusRequestDto busRequestDto) throws IOException{
         busService.create(busRequestDto);
        return new ResponseEntity<>("created",HttpStatus.OK);
    }

    @RequestMapping(value="/bus/update", method=RequestMethod.POST)
    public APIResponse update (@RequestBody BusRequestDto busRequestDto) throws IOException{
        return busService.update(busRequestDto);
    }

    @RequestMapping(value="/bus/delete/{busId}", method=RequestMethod.DELETE)
    public APIResponse delete (@PathVariable int busId){
        return busService.delete(busId);
    }

    @RequestMapping(value="/bus/get/{busId}", method=RequestMethod.GET)
    public ResponseEntity<String> get (@PathVariable int busId){
        busService.get(busId);
        return new ResponseEntity<>("Displayed",HttpStatus.OK);
    }

    @RequestMapping(value="/get/", method=RequestMethod.POST)
    private APIResponse getBusByCustom (@RequestBody BusDaoFilter busDaoFilter){
        return busService.getBusByCustom(busDaoFilter);
    }

    @RequestMapping(value="/bus/get/buses", method=RequestMethod.GET)
    private List<Bus> getBuses (BusResponseDto busResponseDto) throws IOException{
        List<Bus> list=  busService.getBuses();
//        System.out.println(list.get(1));
//        System.out.println(list);
//        FileWriter fileWriter = new FileWriter("buslist//bus2.txt",false);
//        fileWriter.write(list.toString());
//        fileWriter.close();
        return list;
    }

//    @GetMapping("check")
//    private List<Bus> getData()
//    {
//        List<Bus> list = busService.getData();
//        return list;
//    }

    @PostMapping("/getBus")
    public Page<Bus> getBusWithFilter (@RequestBody BusPage busPage){
        return busImp.findAllWithFilter(busPage);
    }

}
