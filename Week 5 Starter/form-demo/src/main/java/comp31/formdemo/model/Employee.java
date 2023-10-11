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
    String department;

    //Constructor for creating a new Employee instance.
    public Employee(String userId,String firstName,String lastName,String password, String department) {
        super();//Call the superclass constructor
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.department = department;
    }

    //getter for UserId
    public String getUserId() {
        return this.userId;
    }

    //setter for UserId
    public void setUserId(String userId) {
        this.userId = userId;
    }

    //getter for FirstName
    public String getFirstName() {
        return this.firstName;
    }

    //setter for FirstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //getter for LastName
    public String getLastName() {
        return this.lastName;
    }

    //setter for LastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //getter for Password
    public String getPassword() {
        return this.password;
    }

    //setter for Password
    public void setPassword(String password) {
        this.password = password;
    }

    //getter for our getDepartment()
    public String getDepartment() {
        return this.department;
    }

    //setter for our setDepartment()
    public void setDepartment(String department) {
        this.department = department;
    }

                

}
