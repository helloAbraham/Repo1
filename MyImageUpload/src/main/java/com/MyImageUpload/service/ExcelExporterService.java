package com.MyImageUpload.service;

import com.MyImageUpload.model.ContactUs;
import com.MyImageUpload.model.Style;
import com.MyImageUpload.repository.ContactUsRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

//Service to Fetch Data and Generate Excel

/**
 Next, you need a service that will query the database to fetch the ContactUs
 records and generate an Excel file using Apache POI.
 */
@Service
public class ExcelExporterService {
    @Autowired
    private ContactUsRepository contactUsRepository;
//    private CellStyle lockedStyle;

    //Create CellStyle based on the custom Style
    public CellStyle createCellStyleNew(Workbook workbook, Style style) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();

        //Set properties
        font.setFontHeightInPoints(style.getFontSize());
        font.setBold(style.isBold());

        //Set the locked property to make the cell non-editable
        cellStyle.setLocked(style.isLocked());

        //Apply the font to the cell style
        cellStyle.setFont(font);
        return cellStyle;
    }

    //Generate Excel file and return as byte array
    @Transactional
    public byte[] generateContactUsExcelReport() throws IOException {
        List<ContactUs> contactUsList = contactUsRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        //Create a cell style using the Style builder pattern with locked true
        Style lockedStyle = Style.builder()
                .locked(true) //Set cells as locked
                .fontSize((short) 12)
                .bold(true)
                .alignment(HorizontalAlignment.CENTER)
                .build();

        //Create a cell style using the Style builder pattern with locked false
        Style unlockedStyle = Style.builder()
                .locked(false)
                .fontSize((short)12)
                .bold(false)
                .alignment(HorizontalAlignment.CENTER)
                .build();

        //Apply locked style to a cell
        CellStyle lockedCellStyle = createCellStyleNew(workbook, lockedStyle);

        //Apply unlocked style to a cell
        CellStyle unlockedCellStyle = createCellStyleNew(workbook, unlockedStyle);


        //Create a row style to a cell
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Phone Number");
        headerRow.createCell(3).setCellValue("Email");
        headerRow.createCell(4).setCellValue("Message");
// Unlock all cells first (this is the default behavior, but we do it explicitly here)
        sheet.protectSheet("");  // This will protect all locked cells once we apply the lock
//        CellStyle lockedTheCell = workbook.createCellStyle();
//        lockedTheCell.setLocked(true);
        //It is time now fill the sheet with data from the ContactUs record
        int rowNum = 1; //Starts after the header row
        for(ContactUs contactUs: contactUsList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(contactUs.getId());
            //let's this one editable
            //row.createCell(1).setCellValue(contactUs.getName());
            Cell nameCell = row.createCell(1);
            nameCell.setCellValue(contactUs.getName());
            nameCell.setCellStyle(unlockedCellStyle);

            //inline style
//            Cell phoneNumberCell = row.createCell(2);
//            phoneNumberCell.setCellValue(contactUs.getPhoneNumber());
//
//            CellStyle lockedTheCell = workbook.createCellStyle();
//            lockedTheCell.setLocked(true);
//
//            phoneNumberCell.setCellStyle(lockedTheCell);

            Cell phoneNumberCell = row.createCell(2);
            phoneNumberCell.setCellValue(contactUs.getPhoneNumber());
            phoneNumberCell.setCellStyle(lockedCellStyle);


//            row.createCell(2).setCellStyle(lockedCellStyle);
//            row.createCell(2).setCellValue(contactUs.getPhoneNumber());
            row.createCell(3).setCellValue(contactUs.getEmail());
            row.createCell(4).setCellValue(contactUs.getMessage());
        }
//        Cell cell = row.createCell(0);
//        cell.setCellValue("This cell is locked and non-editable.");
//        cell.setCellStyle(lockedCellStyle);

        //Protect the sheet so locked cells can't be edited
        sheet.protectSheet("Password123");
        //sheet.setDefaultColumnWidth(15);  // Optional: set default column width
        sheet.autoSizeColumn(0);  // Optional: Automatically adjust column width for ID
        sheet.autoSizeColumn(1);  // Optional: Automatically adjust column width for Name
        sheet.autoSizeColumn(2);  // Optional: Automatically adjust column width for Phone Number
        sheet.autoSizeColumn(3);  // Optional: Automatically adjust column width for Email
        sheet.autoSizeColumn(4);  // Optional: Automatically adjust column width for Message
        // Allow resizing columns even when the sheet is protected

        //Write the workbook to byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        //Close the workbook
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }
}
