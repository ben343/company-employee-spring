package companyEmployee.Service;

import companyEmployee.enttity.Company;

import companyEmployee.repositiry.CompanyRepository;

import companyEmployee.repositiry.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public void save(Company company) {
        companyRepository.save(company);
    }

    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }

}
