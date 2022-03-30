package com.example.windows;

public class Employee {
    String EmployeeNumber;
    String FirstName;
    String LastName;
    String Title;

    public Employee(String employeeNumber, String firstName, String lastName, String title) {
        EmployeeNumber = employeeNumber;
        FirstName = firstName;
        LastName = lastName;
        Title = title;
    }

    public String getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        EmployeeNumber = employeeNumber;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
