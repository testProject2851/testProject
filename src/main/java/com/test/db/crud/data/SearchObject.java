package com.test.db.crud.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SearchObject {
    private String name;
    private Integer age;
    private Boolean admin;
    private Date createdDate;
    private Long id;

    public SearchObject() {
    }

    public SearchObject(Long id, String name, Integer age, Boolean admin, String createdDate) {
        this.name = name;
        this.age = age;
        this.admin = admin;

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        if (createdDate != null) {
            try {
                this.createdDate = format.parse(createdDate);
            } catch (ParseException e) {

            }
        }

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean nothingToSearch() {
        return name == null && age == null && admin == null && id == null && createdDate == null;
    }
}