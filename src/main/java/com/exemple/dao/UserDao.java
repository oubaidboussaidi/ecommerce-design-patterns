package com.exemple.dao;

import com.exemple.model.User;
import com.exemple.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao implements IUserDao {

    @Override
    public void save(User u) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
            System.out.println("User saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User verifyUser(String login, String password) {
        User verifiedUser = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Query<User> query = session.createQuery(
                    "FROM User WHERE login = :login AND motdepasse = :password", User.class);
            query.setParameter("login", login);
            query.setParameter("password", password);

            verifiedUser = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verifiedUser;
    }

    @Override
    public boolean isEmailExists(String email) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> searchUsersByEmail(String emailFilter) {
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
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
    @Override
    public void deleteByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Query<?> query = session.createQuery("DELETE FROM User WHERE login = :email");
            query.setParameter("email", email);
            int result = query.executeUpdate();

            session.getTransaction().commit();
            System.out.println("Deleted " + result + " user(s) with email: " + email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserRole(String email, String newRole) {
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            Query<?> query = session.createQuery("UPDATE User SET role = :newRole WHERE login = :email");
            query.setParameter("newRole", newRole);
            query.setParameter("email", email);
            int result = query.executeUpdate();

            session.getTransaction().commit();
            System.out.println("Updated role for user with email: " + email + " to " + newRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
