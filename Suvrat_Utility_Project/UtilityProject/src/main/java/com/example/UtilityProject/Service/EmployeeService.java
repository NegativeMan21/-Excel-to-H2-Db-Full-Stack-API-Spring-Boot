package com.example.UtilityProject.Service;

import com.example.UtilityProject.ExcelToDb;
import com.example.UtilityProject.Model.Employee;
import com.example.UtilityProject.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    public void save(MultipartFile file){

        try {
            List<Employee> employees = ExcelToDb.ExcelToDatabaseEmp(file.getInputStream());
            this.employeeRepo.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Employee> getEmployees(){
        return this.employeeRepo.findAll();
    }
}
