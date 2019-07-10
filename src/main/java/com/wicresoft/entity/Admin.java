package com.wicresoft.entity;

public class Admin {
    private Integer id;
    private String account;
    private String password;
    private Long created;


    public void setId(Integer id){
        this.id=id;
    }
    public Integer getId(){
        return this.id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                '}';
    }
}
