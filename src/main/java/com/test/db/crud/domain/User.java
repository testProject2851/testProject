package com.test.db.crud.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;



@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.getUsersWithFilter", query = "SELECT u FROM User u WHERE u.name LIKE '%'+:filter+'%'"),
        @NamedQuery(name = "User.getUsers", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.getUserById", query = "SELECT u FROM User u WHERE u.id = :id"),
        @NamedQuery(name = "User.countUsers", query = "SELECT COUNT(u.id) as cnt FROM User u")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(25)")
    private String name;

    @Column
    private Integer age;

    @Column (name = "isAdmin")
    private Boolean admin;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @PrePersist
    public void preMerge() {
        if (createdDate == null) {
            createdDate = new Date();
        }
    }
}