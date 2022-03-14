package companyEmployee.controller;


import companyEmployee.Service.CompanyService;
import companyEmployee.Service.EmployeeService;
import companyEmployee.enttity.Employee;
import companyEmployee.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @Value("${employee.upload.path}")
    public String imagePath;

    @GetMapping("/employees")
    public String allEmployees(ModelMap map, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Employee> employees = employeeService.findAll();
        map.addAttribute("employees", employees);
        map.addAttribute("currentUser", currentUser);
        map.addAttribute("companies", companyService.findAll());
        return "employees";
    }

    @GetMapping("/successLogin")
    public String successLogin() {
        return "redirect:/employees";
    }

    @GetMapping("/register")
    public String register(ModelMap map, @AuthenticationPrincipal CurrentUser currentUser) {
        map.addAttribute("currentUser", currentUser);
        map.addAttribute("companies", companyService.findAll());
        return "register";
    }


    @PostMapping("/addEmployee")
    public String addEmployees(@ModelAttribute Employee employee,
                               @RequestParam("image") MultipartFile uploadedFile) throws IOException {

        employeeService.addEmployee(employee, uploadedFile);
        return "redirect:/employees";
    }

}

;