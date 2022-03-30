package com.example.windows;


public class Customer{
    String AccountNumber;
    String FirstName;
    String LastName;
    String PhoneNumber;
    String EmergencyName;
    String EmergencyPhone;

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmergencyName() {
        return EmergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        EmergencyName = emergencyName;
    }

    public String getEmergencyPhone() {
        return EmergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        EmergencyPhone = emergencyPhone;
    }

    public Customer(String accountNumber, String firstName, String lastName, String phoneNumber, String emergencyName, String emergencyPhone) {
        AccountNumber = accountNumber;
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
        EmergencyName = emergencyName;
        EmergencyPhone = emergencyPhone;
    }
}