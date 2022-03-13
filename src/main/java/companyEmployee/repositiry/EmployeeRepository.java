package companyEmployee.repositiry;

import companyEmployee.enttity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAll();

    void deleteEmployeeByCompanyId(int id);

}
