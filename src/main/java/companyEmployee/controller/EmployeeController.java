package companyEmployee.controller;

import companyEmployee.enttity.Company;
import companyEmployee.enttity.Employee;
import companyEmployee.repositiry.CompanyRepository;
import companyEmployee.repositiry.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Value("${employee.upload.path}")
    public String imagePath;

    @GetMapping("/employees")
    public String allEmployees(ModelMap map) {
        List<Employee> employees = employeeRepository.findAll();
        map.addAttribute("companies", companyRepository.findAll());
        map.addAttribute("employees", employees);
        return "employees";
    }


    @PostMapping("/addEmployee")
    public String addEmployees(@ModelAttribute Employee employee,
                               @RequestParam("image") MultipartFile uploadedFile) throws IOException {

        if (!uploadedFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
            File newFile = new File(imagePath + fileName);
            uploadedFile.transferTo(newFile);
            employee.setPicUrl(fileName);
        }

        employeeRepository.save(employee);
        return "redirect:/employees";
    }

}

