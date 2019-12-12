package DatabaseProject.models;

import DatabaseProject.core.annotations.Entity;
import DatabaseProject.core.annotations.Id;


@Entity
public class User {
    @Id
    private Integer userID;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String personalID;
    private Role role;
    private double GPA;
    private UniversityDegree degree;

    public User(Integer userID, String firstName, String lastName, String userName, String password, String personalID, Role role, double gpa, UniversityDegree degree) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.personalID = personalID;
        this.role = role;
        this.GPA = gpa;
        this.degree = degree;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double gpa) {
        this.GPA = gpa;
    }

    public UniversityDegree getDegree() {
        return degree;
    }

    public void setDegree(UniversityDegree degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", personalID='" + personalID + '\'' +
                ", role=" + role +
                ", GPA=" + GPA +
                ", degree=" + degree +
                '}';
    }
}

