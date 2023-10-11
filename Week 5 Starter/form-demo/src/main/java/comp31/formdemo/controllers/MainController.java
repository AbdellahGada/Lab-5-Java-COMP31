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

    Logger logger = LoggerFactory.getLogger(MainController.class);

    LoginService loginService;

    public MainController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")//GET request for the root URL ("/") and displays the login form.
    String getRoot(Model model) {
        logger.info("---- At root.");
         // Create a new Employee object to serve as the model for the login form
        Employee employee = new Employee(); 
        model.addAttribute("employee", employee); 
        // Return the name of the view template for the login form
        return "login-form";
    }

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
            logger.info("--else--" + currentUser.toString());
    
            if (currentUser.getDepartment() != null) {
                model.addAttribute("employee", currentUser);
    
                switch (currentUser.getDepartment()) {
                    case "admin":
                        returnPage = "departments/admin";
                        break;
                    case "orders":
                        returnPage = "departments/orders";
                        break;
                    case "repairs":
                        returnPage = "departments/repairs";
                        break;
                    case "sales":
                        returnPage = "departments/sales";
                        break;
                    default:
                        returnPage = "welcome";
                }
            } else {
                returnPage = "welcome";
            }
        }
    
        return returnPage;
    }

    @GetMapping("/register")
    public String getRegister(Model model)
    { 
        // Create a new Employee object to serve as the model for the form
        model.addAttribute("employee", new Employee());
        // Return the name of the view template for the registration form
        return "register-form";
    }

    @GetMapping("/add-employee")
    public String getAddEmployee(Model model)
    {
        // Create a new Employee object to serve as the model for the form
        model.addAttribute("employee", new Employee());
        // Return the name of the view template for the form to add a new employee
        return "login-form";
    }

    @PostMapping("/add-employee")
    public String postAddEmployee( Model model, Employee newemployee )
    {
        // Add the new employee to the system via the login service
        loginService.addemployee(newemployee);
        // Add the new employee to the model
        model.addAttribute("employee", newemployee);
        // Redirect to the admin page after adding the employee
        return "redirect:/admin";
    }

    // POST request to register a new employee
    @PostMapping("/register")
    public String postRegister( Model model, Employee newemployee )
    {
        // Register the new employee via the login service
        loginService.addemployee(newemployee);
        // Add the registered employee to the model
        model.addAttribute("employee", newemployee);
        // Return the name of the view template to display after registration
        return "login-form";
    }

}
