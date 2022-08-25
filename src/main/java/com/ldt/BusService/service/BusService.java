package com.ldt.BusService.service;
import com.ldt.BusService.entity.Bus;
import com.ldt.BusService.model.BusDaoFilter;
import com.ldt.BusService.model.BusRequestDto;
import com.ldt.BusService.util.APIResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
public interface BusService{

    APIResponse create(BusRequestDto busRequestDto) throws IOException;

    APIResponse update(BusRequestDto busRequestDto) throws IOException;

    APIResponse delete(int busId);

    APIResponse get(int busId);

    List<Bus> getBuses();

    APIResponse getBusByCustom(BusDaoFilter busDaoFilter);

    Bus  store(MultipartFile file) throws IOException;

    ByteArrayInputStream load();

//    ByteArrayInputStream download();

//    List<Bus> getData();


}
