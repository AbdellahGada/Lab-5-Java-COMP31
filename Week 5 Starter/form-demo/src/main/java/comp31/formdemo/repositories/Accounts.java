package comp31.formdemo.repositories;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import comp31.formdemo.model.Employee;

@Component
public class Accounts extends ArrayList<Employee> {

    public Employee findByUserId(String userId) {
        for (Employee employee : this) {
            if (employee.getUserId().equals(userId))
                return employee;
        }
        return null;
    }

    // Find all employees in a specific department
    public ArrayList<Employee> findAllEmployeesInDepartment(String department) {
        ArrayList<Employee> employeesInDepartment = new ArrayList<>();
        for (Employee employee : this) {
            if (employee.getDepartment().equals(department)) {
                employeesInDepartment.add(employee);
            }
        }
        return employeesInDepartment;
    }

    // Find all employees
    public ArrayList<Employee> findAllEmployees() {
        return this;
    }

    // Verify user based on user ID and password
    public boolean verifiedUser(Employee checkEmployee) {

        if (checkEmployee != null) {
            for (Employee employee : this) {
                if (employee.getUserId().equals(checkEmployee.getUserId())
                        && employee.getPassword().equals(checkEmployee.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<String> findAllDepartment() {
        ArrayList<String> department = new ArrayList<String>();
        for (Employee employee : this) {
            if (!department.contains(employee.getDepartment())) {
                department.add(employee.getDepartment());
            }
        }
        return department;
    }

}
