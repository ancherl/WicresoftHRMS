package com.wicresoft.entity;

public class HRDepartment {
    private Integer id;
    private String departmentName;
    private String manager;

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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "HRDepartment{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }
}
