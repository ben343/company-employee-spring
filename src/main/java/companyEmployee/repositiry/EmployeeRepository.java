package companyEmployee.repositiry;

import companyEmployee.enttity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByName(String name);

    @Transactional
    void deleteEmployeeByCompanyId(int id);
}
