package comp31.formdemo.repositories;

import java.util.ArrayList;
import java.util.List;

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

    // TODO add findByDepartment
    public List<Employee> findByDepartment(String department) {
        List<Employee> employeesInDepartment = new ArrayList<>();
        for (Employee employee : this) {
            if (employee.getDepartment().equals(department)) {
                employeesInDepartment.add(employee);
            }
        }
        return employeesInDepartment;
    }
    // TODO add findAllEmployees
    public List<Employee> findAllEmployees() {
        return new ArrayList<>(this);
    }

}
