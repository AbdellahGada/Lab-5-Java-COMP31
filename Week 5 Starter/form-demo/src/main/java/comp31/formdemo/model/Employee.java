package comp31.formdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

    String userId;
    String firstName;
    String lastName;
    String password;
    String role;
    String department;

    public Employee(String userId) {
        super();
        this.userId = userId;
    }

  

}
