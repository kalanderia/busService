package com.ldt.BusService.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ldt.BusService.util.BusReservationStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bus")
public class Bus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     private String name;
     private String type;
     private String number;

     @Enumerated(EnumType.STRING)
     private BusReservationStatus busReservationStatus;

     @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
     private LocalDateTime registeredAt = LocalDateTime.now();

     private int status = 1;



 @Override
 public String toString (){
  return "Bus{" + "id=" + id + ", name='" + name + '\'' + ", type='" + type + '\'' + ", number='" + number + '\'' + ", busReservationStatus=" + busReservationStatus + ", registeredAt=" + registeredAt + ", status=" + status + '}';
 }


     private String fileName;
     private String fileType;

     @Lob
     private byte[] data;


 public Bus (String fileName,String contentType,byte[] data){
  this.fileName = fileName;
  this.fileType = contentType;
  this.data = data;
 }
 public String getFileName (){
  return fileName;
 }

 public void setFileName (String fileName){
  this.fileName = fileName;
 }

 public String getFileType (){
  return fileType;
 }

 public void setFileType (String fileType){
  this.fileType = fileType;
 }

 public byte[] getData (){
  return data;
 }

 public void setData (byte[] data){
  this.data = data;
 }
}
