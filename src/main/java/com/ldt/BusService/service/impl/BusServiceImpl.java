package com.ldt.BusService.service.impl;
import com.ldt.BusService.entity.Bus;
import com.ldt.BusService.model.BusDaoFilter;
import com.ldt.BusService.model.BusDaoResponseFilter;
import com.ldt.BusService.model.BusRequestDto;
import com.ldt.BusService.model.BusResponseDto;
import com.ldt.BusService.repository.BusRepository;
import com.ldt.BusService.service.BusService;
import com.ldt.BusService.util.APIResponse;
import com.ldt.BusService.util.BusReservationStatus;
import com.ldt.BusService.util.ExcelHelper;
import com.sun.xml.internal.ws.api.message.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService{
    @Autowired
    private BusRepository busRepository;
    @Override
    public APIResponse create(BusRequestDto busRequestDto) throws IOException{
        Bus bus=new Bus();
        bus=getBusEntityFrom(bus,busRequestDto);
        busRepository.save(bus);
        return new APIResponse("Success","200","Bus Created",null);
    }

    private Bus getBusEntityFrom(Bus bus,BusRequestDto busRequestDto){
        bus.setName(busRequestDto.getName());
        bus.setType(busRequestDto.getType());
        bus.setNumber(busRequestDto.getNumber());
        bus.setBusReservationStatus(busRequestDto.getBusReservationStatus());
        return bus;
    }

    @Override
    public APIResponse update(BusRequestDto busRequestDto){
        Bus bus=busRepository.findById(busRequestDto.getBusId());
        bus.setName(busRequestDto.getName());
        bus.setNumber(busRequestDto.getNumber());
        bus.setType(busRequestDto.getType());
        bus.setStatus(busRequestDto.getStatus());
        busRepository.save(bus);

        return new APIResponse("Success","200","Bus Details Updated",null);
    }
    @Override
    public APIResponse delete(int busId){
        Bus bus=busRepository.findById(busId);
        busRepository.delete(bus);
        return new APIResponse("Success","200","Successfully Deleted",null);
    }
    @Override
    public APIResponse get(int busId){
        Bus bus=busRepository.findById(busId);
        return new APIResponse("Success","200",null,getResponseDtoFrom(bus));
    }
    private BusResponseDto getResponseDtoFrom(Bus bus){
        BusResponseDto busResponseDto=new BusResponseDto();
        busResponseDto.setBusId(bus.getId());
        busResponseDto.setBusName(bus.getName());
        busResponseDto.setBusType(bus.getType());
        busResponseDto.setBusNumber(bus.getNumber());
        busResponseDto.setBusReservationStatus(bus.getBusReservationStatus());
        busResponseDto.setStatus(bus.getStatus());
        return busResponseDto;
    }
    @Override
    public List<Bus> getBuses(){
        List<Bus> list=new ArrayList<>();
        list=busRepository.findAll();
        return list;
    }
    @Override
    public APIResponse getBusByCustom(BusDaoFilter busDaoFilter){

         String name = busDaoFilter.getName();
        if (name == null || name == "" || Objects.isNull(name)) {

            name = "0";
        }
         String type = busDaoFilter.getType();
        if (type == null || type == "" || Objects.isNull(type)) {

            type = "0";
        }

         String number = busDaoFilter.getNumber();
        if(number == null || number == "" || Objects.isNull(number))
        {
            number= "0";
        }

        String busReservationStatus =String.valueOf(busDaoFilter.getBusReservationStatus());
        if(busReservationStatus == null || busReservationStatus == "" || busReservationStatus == "null" ){
            busReservationStatus = "0";
        }
        List<Bus> busList = busRepository.getBusByCustom(busDaoFilter.getOffset(),busDaoFilter.getLimit(),name, type, number,busReservationStatus);
        int count = busRepository.getFilterByCount(name,type,number,busReservationStatus);
        List<BusDaoResponseFilter> busDaoResponseFilter = new ArrayList<>();
        if (!busList.isEmpty()) {
            busDaoResponseFilter= busList.stream().map(this::getBusDaoResponseFilterFrom).collect(Collectors.toList());
            busDaoFilter.setCount(count);
            return new APIResponse("Success", "success", "found", count, busDaoResponseFilter);
        }
        List<Bus> list1 = new ArrayList<>();
        return new APIResponse("Success", "200","NotFound", count,list1 );
    }



//    @Override
//    public Attachment saveAttachment (MultipartFile file){
//       String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//       try
//       {
//           if(fileName.contains(".."))
//           {
//              throw new Exception("Filename contains invalid path sequences" + fileName);
//              Bus bus = new Bus(fileName,file.getContentType(),file.getBytes());
//           }
//       }catch (Exception e)
//       {
//           e.printStackTrace();
//       }
//        return null;
//    }


    private BusDaoResponseFilter getBusDaoResponseFilterFrom(Bus bus){
        BusDaoResponseFilter busDaoResponseFilter=new BusDaoResponseFilter();
        busDaoResponseFilter.setBusName(bus.getName());
        busDaoResponseFilter.setBusType(bus.getType());
        busDaoResponseFilter.setBusNumber(bus.getNumber());
        busDaoResponseFilter.setBusReservationStatus(bus.getBusReservationStatus());
        return busDaoResponseFilter;
    }

    @Override
    public Bus store (MultipartFile file) throws IOException{
        byte[] data = file.getBytes();
        String s = String.valueOf(data);
        String name = file.getName();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Bus bus = new Bus(fileName,file.getContentType(),file.getBytes());
        return busRepository.save(bus);
    }

    @Override
    public ByteArrayInputStream load() {
        List<Bus> busList = busRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = ExcelHelper.busDetailsToExcel(busList);
        return byteArrayInputStream;
    }
//
//    @Override
//    public List<Bus> getData()
//    {
//        List<Bus> list = busRepository.getData();
//        return list;
//    }



}
