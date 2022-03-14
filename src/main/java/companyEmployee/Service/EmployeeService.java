package companyEmployee.Service;

import companyEmployee.enttity.Employee;
import companyEmployee.enttity.Role;
import companyEmployee.repositiry.CompanyRepository;
import companyEmployee.repositiry.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private  final PasswordEncoder passwordEncoder;

    @Value("${employee.upload.path}")
    public String imagePath;


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    public void addEmployee(@ModelAttribute Employee employee,
                            @RequestParam("image") MultipartFile uploadedFile) throws IOException {
        if (!uploadedFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
            File newFile = new File(imagePath + fileName);
            uploadedFile.transferTo(newFile);
            employee.setPicUrl(fileName);


        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(Role.USER);
        employeeRepository.save(employee);
    }

    public void deleteEmployeesByCompany_Id(int id) {
        employeeRepository.deleteEmployeeByCompanyId(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}