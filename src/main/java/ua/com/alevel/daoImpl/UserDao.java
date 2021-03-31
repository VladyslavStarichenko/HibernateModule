package ua.com.alevel.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ua.com.alevel.dao.Dao;
import ua.com.alevel.model.User;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;


public class UserDao implements Dao<User> {
    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public void createUser(String userMail, User user, String massage) {
        if (checkUserExisting(userMail)) {
            System.out.println(massage);
        } else if (user.getAge() > 18) {
            create(user);
        }
    }

    @Override
    public void create(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean checkUserExisting(String userMail) {
        List<User> users = findAll();
        boolean flag = false;
        for (User user : users) {
            if (user.getEmail().equals(userMail)) {
                return true;
            } else {
                return false;
            }
        }
        return flag;
    }

    @Override
    public void update(User newUser, int userId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE User " +
                    "SET " +
                    "fullName =: full_name, " +
                    "login =: login, " +
                    "email =: email, " +
                    "age =: age, " +
                    "isAuthor =: is_author, " +
                    "isModerator =: is_moderator " +
                    "WHERE id =: userId");
            query.setParameter("full_name", newUser.getFullName());
            ;
            query.setParameter("login", newUser.getLogin());
            ;
            query.setParameter("email", newUser.getEmail());
            ;
            query.setParameter("age", newUser.getAge());
            ;
            query.setParameter("is_author", newUser.getAuthor());
            ;
            query.setParameter("is_moderator", newUser.getModerator());
            ;
            query.setParameter("userId", userId);
            int rows = query.executeUpdate();
            session.getTransaction().commit();
            if (rows > 0) {
                System.out.println("User is successfully updated");
            }
        }
}

    @Override
    public User findById(int userId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("  FROM  User where id =: userId");
            query.setParameter("userId", userId);
            User singleResult = (User) query.getSingleResult();
            session.getTransaction().commit();
            return singleResult;
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User");
            List<User> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    public void showUser() {
        List<User> categoryList = findAll();
        for (User user : categoryList) {
            System.out.println("\n" + user + "\n");
        }
    }

    @Override
    public boolean deleteById(int id) {
        int rows;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE id =: id");
            query.setParameter("id", id);
            rows = query.executeUpdate();
            session.getTransaction().commit();
        }
        if (rows > 0){
            return true;
        }else{
            return false;
        }
    }
}
