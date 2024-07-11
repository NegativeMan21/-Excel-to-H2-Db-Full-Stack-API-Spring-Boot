package com.example.UtilityProject;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ExcelToDatabase {
    private static final Logger logger = LogManager.getLogger(ExcelToDatabase.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void dumpExcelToDatabase(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", filePath);
            e.printStackTrace();
        } catch (IOException | EncryptedDocumentException e) {
            logger.error("Error reading Excel file: {}", filePath);
            e.printStackTrace();
        }
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Double ID = row.getCell(0).getNumericCellValue();
                String FirstName = row.getCell(1).getStringCellValue();
                String LastName = row.getCell(2).getStringCellValue();
                Double Age = row.getCell(3).getNumericCellValue();
                String Email = row.getCell(4).getStringCellValue();
                String PhoneNumber = row.getCell(5).getStringCellValue();

                String sql = "INSERT INTO EMPLOYEE ( ID, FirstName, LastName,Age,Email,PhoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
                jdbcTemplate.update(sql, ID, FirstName, LastName,Age,Email,PhoneNumber);
                logger.info("Inserted data: ID={}, FirstName={}, LastName={}, Age={}, Email={}, PhoneNumber={}",
                        ID, FirstName, LastName, Age, Email, PhoneNumber);
            }
        } catch (IOException | EncryptedDocumentException e) {
            logger.error("Error reading Excel file: {}", filePath);
            e.printStackTrace();
        }
    }
}
