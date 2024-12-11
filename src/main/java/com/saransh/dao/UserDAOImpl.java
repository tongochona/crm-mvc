package com.saransh.dao;

import com.saransh.entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory factory;

    @Override
    public void addUser(User user) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public User getUser(int id) {
        Session session = factory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public List<User> getUsers(int sortBy) {
        String sort;
        switch (sortBy) {
            case 0: sort = "firstName";
                break;
            case 2: sort = "email";
                break;
            default:
                sort = "lastName";
        }
        Session session = factory.getCurrentSession(); 
        String queryString = "from User order by " + sort;
        Query<User> query = session.createQuery(queryString, User.class); 
        return query.getResultList(); // execute query and get customers list.
    }

    @Override
    public void deleteUser(int id) {
        Session session = factory.getCurrentSession(); // get hibernate session.
        Query query = session.createQuery("delete from User where id=:userID");
        query.setParameter("userID", id);
        query.executeUpdate(); // delete the customer from the database.
    }

    @Override
    public List<User> searchUser(String searchString) {
        Session session = factory.getCurrentSession();
        Query<User> query = session.createQuery("from User where firstName like :search or lastName like :search or email like :search", User.class);
        searchString = "%" + searchString + "%";
        query.setParameter("search", searchString);
        return query.getResultList();
    }
    
    @Override
    public User findByUsername(String username) {
        Session session = factory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
