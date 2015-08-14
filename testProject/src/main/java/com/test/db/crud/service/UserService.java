package com.test.db.crud.service;

import com.test.db.crud.dao.UserDAO;
import com.test.db.crud.data.SearchObject;
import com.test.db.crud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
public class UserService {

    private int perPageValue = 20;

    @Autowired
    private UserDAO userDAO;


    public List<User> getUserList(int page) {
        System.out.println("Show from "  + getFrom(page) + " to " + (perPageValue));
        return userDAO.get(getFrom(page), perPageValue);
    }


    public User getUserById(Long id) {
        return userDAO.getById(id);
    }


    public void processUser(User user) {
        userDAO.merge(user);
    }


    public List<User> getUserListWithFilter(String filter, int page) {
        return userDAO.get(filter, getFrom(page), perPageValue);
    }


    public void deleteUserById(Long id) {
        User forDelete = userDAO.getById(id);
        if (forDelete != null)
            userDAO.delete(forDelete);
    }


    public void setPerPageValue(int perPageValue) {
        this.perPageValue = perPageValue;
    }


    public Long total() {
        return userDAO.getCountOfUsers();
    }


    public List<User> getUserListBySearchObject(SearchObject searchObject, int page) {
        System.out.println("Show from "  + getFrom(page) + " to " + (perPageValue - 1));
        return userDAO.getByParams(searchObject.getId(), searchObject.getName(), searchObject.getAge(), searchObject.getAdmin(), searchObject.getCreatedDate(), getFrom(page), perPageValue - 1);
    }


    private int getFrom(int page) {
        if (page < 1) {
            page = 1;
        }

        return ((page - 1 < 0) ? 0 : page - 1) * perPageValue;
    }
}