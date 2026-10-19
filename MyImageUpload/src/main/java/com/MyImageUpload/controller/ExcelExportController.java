package com.MyImageUpload.controller;

import com.MyImageUpload.service.ExcelExporterService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExcelExportController {

    @Autowired
    private ExcelExporterService excelExporterService;

    //Endpoint to download the generated Excel file
    @GetMapping("/download-excel")
    public ResponseEntity<byte[]> downloadExcelReport() {
        try {
            //Generate the Excel file as byte array
            byte[] excelData = excelExporterService.generateContactUsExcelReport();

            //Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Desposition", "attachment; filename=locked_excel_report.xlsx");
            headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            //Return the Excel file as ResponseEntity
            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
        } catch (IOException e) {
            //Handle the exception if file generation fails
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
