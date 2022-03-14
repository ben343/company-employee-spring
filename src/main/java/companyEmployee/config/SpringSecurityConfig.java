package companyEmployee.config;

import companyEmployee.enttity.Role;
import companyEmployee.security.EmployeeDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final EmployeeDetailsImpl employeeDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().permitAll()
                .defaultSuccessUrl("/successLogin", true)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/companies").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/deleteCompany/{id}").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/addCompany").hasAnyRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/employees").permitAll()
                .antMatchers("/addEmployee").permitAll()
                .antMatchers(HttpMethod.GET, "/register").permitAll()
                .anyRequest().authenticated();
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeDetails)
                .passwordEncoder(passwordEncoder());


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }
}
