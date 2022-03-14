package companyEmployee.repositiry;

import companyEmployee.enttity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAll();

    Optional<Employee> findByEmail(String email);

    void deleteEmployeeByCompanyId(int id);

}
