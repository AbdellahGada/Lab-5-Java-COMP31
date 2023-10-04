package comp31.formdemo.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import comp31.formdemo.model.Employee;
import comp31.formdemo.services.LoginService;

@Controller
public class MainController {

    //initializes a logger instance. It uses a LoggerFactory to create a logger associated with the MainController
    Logger logger = LoggerFactory.getLogger(MainController.class);
    LoginService loginService;
    // MainController class. It takes a single parameter of type LoginService. 
    //This constructor is used to create instances of the MainController class and inject a LoginService dependency at the time of object creation.
    public MainController(LoginService loginService) {
        this.loginService = loginService;
    }

    // This method handles a GET request to the root URL ("/").
    // It logs a message, creates an empty Employee object, adds it to the model,
    // and returns the view name "login-form" to be rendered by the associated template.
    @GetMapping("/")
    String getRoot(Model model) {
        logger.info("---- At root.");
        Employee employee = new Employee(); 
        model.addAttribute("employee", employee);
        return "login-form";
    }

    // This method handles a POST request to "/login".
    // It logs information, retrieves a user from a service, and determines the next page to display based on login success.
    @PostMapping("/login")
    public String getForm(Employee employee, Model model) {
        logger.info("---- At /login.");
        logger.info("---- " + employee.toString());
        Employee currentUser = loginService.findByUserId(employee.getUserId());
        String returnPage;
        
        if (currentUser == null) {
            model.addAttribute("employee", employee);
            returnPage = "login-form";
        } else {
            if (employee.getPassword().equals(currentUser.getPassword())) {    
                String department = currentUser.getDepartment();
                model.addAttribute("employee", currentUser);               
                returnPage =  department;
            } else {               
                model.addAttribute("employee", employee);
                returnPage = "login-form";
            }
        }
        return returnPage;
    }

    // This method handles a GET request to "/add-employee".
    // It prepares and returns a form for adding a new employee by adding an empty Employee object to the model.
    @GetMapping("/add-employee")
    public String getAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee()); 
        return "new-employee-form"; 
    }
    

    //Register Get
    @GetMapping("/register")
    public String getRegister(Model model) 
    {
        model.addAttribute("employee", new Employee());
        return "register-form";
    }

    // This method handles a POST request to "/register".
    // It adds a new employee, clears the form by adding a new empty Employee to the model,
    // and returns the "login-form" view.
    @PostMapping ("/register")
    public String postRegister(Model model, Employee employee)
    {
        loginService.addEmployee(employee);//ADD new employee
        model.addAttribute("employee", new Employee());
        return "login-form";
    }

}
