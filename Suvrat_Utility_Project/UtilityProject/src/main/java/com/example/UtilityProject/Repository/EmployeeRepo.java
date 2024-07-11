package com.example.UtilityProject.Repository;


import com.example.UtilityProject.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

}
