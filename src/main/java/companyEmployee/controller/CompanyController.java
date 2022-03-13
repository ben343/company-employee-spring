package companyEmployee.controller;
import companyEmployee.Service.CompanyService;
import companyEmployee.Service.EmployeeService;
import companyEmployee.enttity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private  final EmployeeService employeeService;

    @GetMapping("/companies")
    public String findAllCompany(ModelMap map) {
        map.addAttribute("companies", companyService.findAll());
        return "companies";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") int id) {
        employeeService.deleteEmployeesByCompany_Id(id);
        companyService.deleteCompany(id);
        return "redirect:/companies";

    }

    @GetMapping("/addCompany")
    public String addCompanyPage() {
        return "companies";


    }

    @PostMapping("/addCompany")
    public String addCompany(@ModelAttribute Company company) {
        companyService.save(company);
        return "redirect:/companies";
    }

}
