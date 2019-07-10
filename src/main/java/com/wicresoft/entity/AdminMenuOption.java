package com.wicresoft.entity;

public class AdminMenuOption {
    private Integer id;
    private String text;
    private String state;
    private String iconCls;
    private String url;
    private Integer child_id;

    public void setId(Integer id){
        this.id=id;
    }
    public Integer getId(){
        return this.id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getChild_id() {
        return child_id;
    }

    public void setChild_id(Integer child_id) {
        this.child_id = child_id;
    }
}
