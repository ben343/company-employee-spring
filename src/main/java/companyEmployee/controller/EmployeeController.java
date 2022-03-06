package companyEmployee.controller;

import companyEmployee.enttity.Company;
import companyEmployee.enttity.Employee;
import companyEmployee.repositiry.CompanyRepository;
import companyEmployee.repositiry.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/employees")
    public String allEmployees(ModelMap map) {
        List<Employee> employees = employeeRepository.findAll();
        map.addAttribute("companies", companyRepository.findAll());
        map.addAttribute("employees", employees);
        return "employees";
    }


    @PostMapping("/addEmployee")
    public String addEmployees(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

}

