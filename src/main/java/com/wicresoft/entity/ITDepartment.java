package com.wicresoft.entity;

public class ITDepartment {
    private Integer id;
    private String departmentName;
    private String location;
    private String departmentManager;

    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }

    @Override
    public String toString() {
        return "ITDepartment{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", location='" + location + '\'' +
                ", departmentManager='" + departmentManager + '\'' +
                '}';
    }
}
