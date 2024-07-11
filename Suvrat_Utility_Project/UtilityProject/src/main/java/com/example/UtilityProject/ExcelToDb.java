package com.example.UtilityProject;

import com.example.UtilityProject.Model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelToDb {
    private static final Logger logger = LogManager.getLogger(ExcelToDb.class);

    public static boolean checkFormat(MultipartFile file){
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        }
        else {
            return false;
        }
    }
    public static List<Employee> ExcelToDatabaseEmp(InputStream is){
        List<Employee> list= new ArrayList<>();
        try{
            XSSFWorkbook workbook =new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Employee");

            int row = 0;
            Iterator<Row> iterator= sheet.iterator();

            while(iterator.hasNext()){
                Row currRow = iterator.next();
                if(row==0){
                    row++;
                    continue;
                }
                Iterator<Cell> cells = currRow.iterator();
                int cid= 0;
                Employee emp = new Employee();
                while(cells.hasNext()){
                    Cell cell =cells.next();
                    switch(cid){
                        case 0:
                            if (cell.getCellType() == CellType.STRING) {
                                emp.setID(Double.valueOf(cell.getStringCellValue()));
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                emp.setID(Double.valueOf(String.valueOf(cell.getNumericCellValue())));
                            }
                            break;
                        case 1:
                            if (cell.getCellType() == CellType.STRING) {
                                emp.setFirstName((cell.getStringCellValue()));
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                emp.setFirstName((String.valueOf(cell.getNumericCellValue())));
                            }
                            break;
                        case 2:
                            if (cell.getCellType() == CellType.STRING) {
                                emp.setLastName((cell.getStringCellValue()));
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                emp.setLastName((String.valueOf(cell.getNumericCellValue())));
                            }
                            break;
                        case 3:
                            if (cell.getCellType() == CellType.STRING) {
                                emp.setAge(Double.valueOf(cell.getStringCellValue()));
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                emp.setAge(Double.valueOf(String.valueOf(cell.getNumericCellValue())));
                            }
                            break;
                        case 4:
                            if (cell.getCellType() == CellType.STRING) {
                                emp.setEmail((cell.getStringCellValue()));
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                emp.setEmail((String.valueOf(cell.getNumericCellValue())));
                            }
                            break;
                        case 5:
                            if (cell.getCellType() == CellType.STRING) {
                                emp.setPhoneNumber((cell.getStringCellValue()));
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                emp.setPhoneNumber((String.valueOf(cell.getNumericCellValue())));
                            }
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(emp);
                logger.info("Extracted data: ID={}, FirstName={}, LastName={}, Age={}, Email={}, PhoneNumber={}",
                        emp.getID(), emp.getFirstName(), emp.getLastName(), emp.getAge(), emp.getEmail(), emp.getPhoneNumber());
            }
        } catch (Exception e){
            logger.error("Error processing Excel file: {}", e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}

