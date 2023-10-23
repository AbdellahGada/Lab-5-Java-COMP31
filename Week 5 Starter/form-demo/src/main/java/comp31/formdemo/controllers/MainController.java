package comp31.formdemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import comp31.formdemo.model.Employee;
import comp31.formdemo.services.AdminService;
import comp31.formdemo.services.LoginService;
import java.util.ArrayList;

@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);
    LoginService loginService;
    AdminService adminService;
    Employee currentLoggingUser;

    public MainController(LoginService loginService, AdminService adminService) {
        this.loginService = loginService;
        this.adminService = adminService;

    }

    @GetMapping({ "/", "/login" }) // GET request for the root URL ("/") and displays the login form.
    String getRoot(Model model) {
        logger.info("---- At root.");
        // Create a new Employee object to serve as the model for the login form
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        // Return the name of the view template for the login form
        return "login-form";
    }

    // Log that we've entered the /login endpoint
    // Log the employee object, which likely contains user input
    // Retrieve the user from the login service using the userId
    // Check if the user is verified
    @PostMapping("/login")
    public String getForm(Employee employee, Model model) {
        logger.info("---- At /login.");
        logger.info("---- " + employee.toString());
        Employee currentUser = loginService.findByUserId(employee.getUserId());
        Boolean verifyUser = loginService.verifiedUser(currentUser);
        // Initialize the return page variable
        String returnPage;
        if (verifyUser) {
            // Log the user object when verification is successful
            logger.info("--else--" + currentUser.toString());
            // Set the currentLoggingUser to the verified user
            currentLoggingUser = currentUser;

            if (currentUser.getDepartment() != null) {
                model.addAttribute("employee", currentUser);
                // Based on the department, determine the return page
                switch (currentUser.getDepartment()) {
                    case "admin":
                        Employee newemployee = new Employee();
                        model.addAttribute("newemployee", newemployee);
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

        } else {
            model.addAttribute("employee", employee);
            returnPage = "login-form";
        }

        return returnPage;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        // Create a new Employee object to serve as the model for the form
        model.addAttribute("employee", new Employee());
        // Return the name of the view template for the registration form
        return "register-form";
    }

    @PostMapping("/add-new-employee")
    public String postAddEmployee(Model model, Employee newemployee) {
        // Add the new employee to the system via the login service
        loginService.addemployee(newemployee);
        // Add the new employee to the model
        // Redirect to the admin page after adding the employee
        return "redirect:/add-new-employee";
    }

    // new getmapping /departments/admin","/add-new-employee"
    @GetMapping({ "/departments/admin", "/add-new-employee" })
    public String getAddEmployee(Model model, Employee newemployee) {
        // Create a new Employee object to serve as the model for the form
        model.addAttribute("newemployee", new Employee());
        model.addAttribute("employee", currentLoggingUser);
        // Return the name of the view template for the form to add a new employee
        return "departments/admin";
    }

    // POST request to register a new employee
    @PostMapping("/register")
    public String postRegister(Model model, Employee newemployee) {
        // Register the new employee via the login service
        loginService.addemployee(newemployee);
        // Add the registered employee to the model
        model.addAttribute("employee", newemployee);
        // Return the name of the view template to display after registration
        return "redirect:/register";
    }

    /*
     * // Controller method to fetch employees based
     * on the 'department' parameter and populate the 'model' for rendering in the
     * 'departments/admin' view.
     * // It supports fetching all employees or
     * employees in specific departments like admin, sales, repairs, or orders.
     * // The list of employees is stored in 'employeeArray,' and attributes are
     * added to the 'model' for rendering.
     */
    @GetMapping("/getAll")
    public String getAll(Model model, @RequestParam String department) {
        ArrayList<Employee> employeeArray = new ArrayList<Employee>();
        if (department.equals("allEmployee")) {
            employeeArray = adminService.findAllEmployees();
        } else if (department.equals("allAdmins")) {

            employeeArray = adminService.findAllEmployeesInDepartment("admin");

        } else if (department.equals("allSales")) {
            employeeArray = adminService.findAllEmployeesInDepartment("sales");

        } else if (department.equals("allRepairs")) {
            employeeArray = adminService.findAllEmployeesInDepartment("repairs");

        } else if (department.equals("allOrders")) {
            employeeArray = adminService.findAllEmployeesInDepartment("orders");

        }
        model.addAttribute("newemployee", new Employee());
        model.addAttribute("employee", currentLoggingUser);
        model.addAttribute("employeeList", employeeArray);
        return "departments/admin";
    }

}
