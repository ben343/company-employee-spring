package companyEmployee.security;

import companyEmployee.enttity.Employee;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


public class CurrentUser extends User {

    private Employee employee;

    public CurrentUser(Employee employee) {

        super(employee.getSurname(), employee.getPassword(), AuthorityUtils.createAuthorityList(employee.getRole().name()));
        this.employee = employee;

    }

    public Employee getEmployee() {
        return employee;
    }
}
