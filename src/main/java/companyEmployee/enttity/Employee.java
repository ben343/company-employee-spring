package companyEmployee.enttity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone_Number;
    private double salary;
    private String position;
    private String picUrl;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

}
