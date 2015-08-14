package com.test.db.crud.dao;

import com.test.db.crud.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Repository
public class UserDAO {
    @PersistenceContext(name = "db")
    private EntityManager em;


    public List<User> get(int from, int count) {
        return em.createNamedQuery("User.getUsers", User.class).setFirstResult(from).setMaxResults(count).getResultList();
    }


    public User getById(Long id) {
        return em.createNamedQuery("User.getUserById", User.class).setParameter("id", id).getSingleResult();
    }


    public void delete(User user) {
        em.remove(user);
    }


    public void merge(User user) {
        em.merge(user);
    }


    public List<User> get(String filter, int from, int count) {
        return em.createNamedQuery("User.getUsersWithFilter", User.class).setParameter("filter", filter).setFirstResult(from).setMaxResults(count).getResultList();
    }


    public Long getCountOfUsers() {
        return em.createNamedQuery("User.countUsers", Long.class).getSingleResult();
    }


    public List<User> getByParams(Long id, String name, Integer age, Boolean admin, Date createdDate, int from, int count) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> tRoot = query.from(User.class);

        query.select(tRoot);

        List<Predicate> criteria = new ArrayList<Predicate>();

        if (id != null) {
            criteria.add(builder.equal(tRoot.get("id"), id));
        }
        if (createdDate != null) {
            criteria.add(builder.equal(tRoot.get("createdDate"), createdDate));
        }
        if (admin != null) {
            criteria.add(builder.equal(tRoot.get("admin"), admin));
        }
        if (name != null) {
            criteria.add(builder.like(tRoot.<String>get("name"), name));
        }
        if (age != null) {
            criteria.add(builder.equal(tRoot.get("age"), age));
        }

        query.where(criteria.toArray(new Predicate[criteria.size()]));

        return em.createQuery(query).setFirstResult(from).setMaxResults(count).getResultList();
    }
}