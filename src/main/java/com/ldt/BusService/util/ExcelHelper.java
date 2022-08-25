package com.ldt.BusService.util;

import com.ldt.BusService.entity.Bus;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper{
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"id","name","number","type","registered_at","bus_reservation_status"};
    static String SHEET = "BusDetails";

    public static ByteArrayInputStream busDetailsToExcel (List<Bus> busList){
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);
            Row headerRow = sheet.createRow(0);
            for(int col = 0; col < HEADERs.length; col++){
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }
            int rowIdx = 1;
            for(Bus bus : busList){
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(bus.getId());
                row.createCell(1).setCellValue(bus.getName());
                row.createCell(2).setCellValue(bus.getNumber());
                row.createCell(3).setCellValue(bus.getType());
                row.createCell(4).setCellValue(bus.getRegisteredAt());
                row.createCell(5).setCellValue(bus.getBusReservationStatus().toString());
            }
            workbook.write(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
