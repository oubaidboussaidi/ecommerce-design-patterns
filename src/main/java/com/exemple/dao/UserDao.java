package com.exemple.dao;

import com.exemple.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {

    private static final SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public UserDao() {
    }

    public void save(User u) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
            System.out.println("User saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User verifyUser(String login, String password) {
        User verifiedUser = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Query<User> query = session.createQuery(
                    "FROM User WHERE login = :login AND motdepasse = :password", User.class);
            query.setParameter("login", login);
            query.setParameter("password", password); // A hasher dans un vrai contexte sécurisé

            verifiedUser = query.uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verifiedUser;
    }

    public boolean isEmailExists(String email) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Query<Long> query = session.createQuery(
                    "SELECT COUNT(*) FROM User WHERE login = :email", Long.class);
            query.setParameter("email", email);

            Long count = query.uniqueResult();
            session.getTransaction().commit();

            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> searchUsersByEmail(String emailFilter) {
        List<User> users = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Query<User> query = session.createQuery(
                    "FROM User WHERE login LIKE :email", User.class);
            query.setParameter("email", "%" + emailFilter + "%");

            users = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
