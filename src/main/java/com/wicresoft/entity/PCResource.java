package com.wicresoft.entity;

public class PCResource {
    private Integer id;
    private String brand;
    private String processor;
    private String memory;
    private String serialNumber;
    private String imageUrl;

    public void setId(Integer id){
        this.id=id;
    }
    public Integer getId(){
        return this.id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "PCResource{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", processor='" + processor + '\'' +
                ", memory='" + memory + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
