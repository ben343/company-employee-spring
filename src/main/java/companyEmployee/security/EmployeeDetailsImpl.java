package companyEmployee.security;

import companyEmployee.enttity.Employee;
import companyEmployee.repositiry.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployeeDetailsImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Employee> byEmail = employeeRepository.findByEmail(username);
        if (!byEmail.isPresent()) {
            throw new UsernameNotFoundException("username" + username + "not found");
        }

        return new CurrentUser(byEmail.get());
    }
}
