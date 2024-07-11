package com.example.UtilityProject.Controller;


import com.example.UtilityProject.ExcelToDb;
import com.example.UtilityProject.Mail.MailService.MailService;
import com.example.UtilityProject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MailService mailService;

    @PostMapping("/employee/upload")
    public String upload(@RequestParam("file")MultipartFile file){
        if(ExcelToDb.checkFormat(file)){
            this.employeeService.save(file);
            triggerMail();
            return "redirect:/success.html";
        }
//        else {
//            triggerMailFail();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an excel file with correct format");
//        }
        return null;
    }
    public void triggerMail(){
        mailService.sendSimpleMessage("suvratprasad21@gmail.com",
                "SUCCESSFUL","Excel file successfully transferred to Database");

    }
    public void triggerMailFail(){
        mailService.sendSimpleMessage("suvratprasad21@gmail.com",
                "FAILED","Excel file failed to transfer to Database");

    }

}
