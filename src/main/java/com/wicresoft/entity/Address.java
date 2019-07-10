package com.wicresoft.entity;

public class Address {
    private Integer address_Id;
    private String province;
    private String cityName;

    public Address(){};

    public Address(Integer address_Id, String province, String cityName){
        this.address_Id=address_Id;
        this.province=province;
        this.cityName=cityName;
    }

    public Integer getAddress_Id() {
        return address_Id;
    }

    public void setAddress_Id(Integer address_Id) {
        this.address_Id = address_Id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address_Id=" + address_Id +
                ", province='" + province + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
