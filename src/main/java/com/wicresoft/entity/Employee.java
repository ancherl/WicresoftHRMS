package com.wicresoft.entity;

public class Employee {
    private Integer id;
    private String employeeName;
    private String department;
    private String email;
    private Address address;

    public Employee (){};

    public Employee(Integer id, String employeeName, String department, String email){
        this.id=id;
        this.employeeName=employeeName;
        this.department=department;
        this.email=email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
