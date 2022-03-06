package companyEmployee.repositiry;

import companyEmployee.enttity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, Integer> {


    List<Company> findAllByName(String name);
}
