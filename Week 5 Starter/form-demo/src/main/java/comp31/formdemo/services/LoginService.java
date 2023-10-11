package comp31.formdemo.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import comp31.formdemo.model.Employee;
import comp31.formdemo.repositories.Accounts;

@Service
public class LoginService {
    // Create a logger for the LoginService class to log messages
    Logger logger = LoggerFactory.getLogger(LoginService.class);
    // Declare the 'accounts' field, which is used to manage employee data
    Accounts accounts;

    public LoginService(Accounts accounts) {
        this.accounts = accounts;
        // Initialize employee data by adding employees to the system
        // Each line creates a new Employee instance and adds it to the system
        this.addemployee(new Employee("sam","Sam","Morgan","qwer1","sales"));
        this.addemployee(new Employee("sally","Sally","Acorn","qwer2","sales"));
        this.addemployee(new Employee("ollie","Ollie","Queen","qwer3","orders"));
        this.addemployee(new Employee("olivia","Olivia","Rivera","qwer4","orders"));
        this.addemployee(new Employee("rachel","Rachel","Piers","qwer5","repairs"));
        this.addemployee(new Employee("ralph","Ralph","Stone","qwer6","repairs"));
        this.addemployee(new Employee("abbie","Abbie","Rohan","qwer7","admin"));
        this.addemployee(new Employee("arthur","Arthur","Perez","qwer8","admin"));

    }

    public void addEmployee(String userId,String firstName,String lastName,String password, String department) {
        logger.info("Adding user: " + userId);
        // Create a new Employee instance with the provided information
        Employee employee = new Employee(userId,firstName,lastName,password,department);
        accounts.add(employee);
    }

    public void addemployee(Employee employee) {
        accounts.add(employee);
    }

    public Employee findByUserId(String userId) {
        return accounts.findByUserId(userId);
    }

    public boolean verifiedUser(String userId,String password)
    {
        return accounts.verifiedUser(userId, password);
    }

    

}
