package comp31.formdemo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import comp31.formdemo.model.Employee;
import comp31.formdemo.repositories.Accounts;

@Service
public class LoginService {

   Logger logger = LoggerFactory.getLogger(LoginService.class);

Accounts accounts;

public LoginService(Accounts accounts) {
    this.accounts = accounts;

    // Hardcode an initial set of users
    String[] userIds = { "sam", "sally", "ollie", "olivia", "rachel", "ralph", "abbie", "arthur" };
    String[] firstNames = { "Samuel", "Sally", "Oliver", "Olivia", "Rachel", "Ralph", "Abigail", "Arthur" };
    String[] lastNames = { "Smith", "Jones", "Lee", "Wang", "Brown", "Green", "White", "Black" };
    String[] departments = { "sales", "sales", "orders", "orders", "repairs", "repairs", "admin", "admin" };

    for (int i = 0; i < userIds.length; i++) {
        addEmployee(userIds[i], firstNames[i], lastNames[i], departments[i]);
    }

}

public void addEmployee(String userId, String firstName, String lastName, String department) {
    logger.info("Adding user: " + userId);
    Employee employee = new Employee();
    employee.setUserId(userId);
    employee.setPassword(userId); // Set the password to be the same as the userId
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setDepartment(department);
    accounts.add(employee);
}

public void addEmployee(Employee employee) {
    accounts.add(employee);
}

public Employee findByUserId(String userId) {
    return accounts.findByUserId(userId);
}
}