package com.ldt.BusService.controller;

import com.ldt.BusService.Message.ResponseMessage;
import com.ldt.BusService.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class FileController{
    @Autowired
    BusService busService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile (@RequestParam("file") MultipartFile file){
        String message = "";
        try {
            busService.store(file);
            message = "Uploaded file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could Not Uploaded: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile (){
        String filename ="busDetails2.xlsx";
        InputStreamResource file = new InputStreamResource(busService.load());
        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION,filename)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + filename).contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

}
