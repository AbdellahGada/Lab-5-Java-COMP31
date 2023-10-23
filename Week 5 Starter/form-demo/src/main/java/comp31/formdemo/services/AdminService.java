package comp31.formdemo.services;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import comp31.formdemo.model.Employee;
import comp31.formdemo.repositories.Accounts;

@Service
public class AdminService {
    Logger logger = LoggerFactory.getLogger(LoginService.class);
    // Declare the 'accounts' field, which is used to manage employee data
    Accounts accounts;

    public AdminService(Accounts accounts) {
        this.accounts = accounts;

    }

    public ArrayList<String> findAllDepartment() {
        return accounts.findAllDepartment();
    }

    public ArrayList<Employee> findAllEmployeesInDepartment(String department) {
        return accounts.findAllEmployeesInDepartment(department);
    }

    public ArrayList<Employee> findAllEmployees() {
        return accounts.findAllEmployees();
    }

}
