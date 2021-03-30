package ua.com.alevel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import ua.com.alevel.model.User;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDao {
    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public static void createUser(String userMail, User user, String massage) {
        if (checkUserExisting(userMail)) {
            System.out.println(massage);
        } else {
            userCreation(user);
        }
    }

    public static boolean checkUserExisting(String userMail) {
        List<User> users = getAllUsers();
        boolean flag = false;
        for (User user : users) {
            if (user.getEmail().equals(userMail)) {
                return true;
            }else{
                return false;
            }
        }
        return flag;
    }

    public static void userCreation(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("  FROM  User ", User.class);
            List<User> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

//    public static User getUser(Integer userId) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            Query query = session.createQuery("  FROM  User where user_id =: userId", User.class);
//            User resultList = (User) query.getSingleResult();
//            session.getTransaction().commit();
//            return resultList;
//        }
//    }

    public static void showUser() {
        List<User> categoryList = getAllUsers();
        for (User user : categoryList) {
            System.out.println("\n" + user + "\n");
        }
    }

    public static void updateUser(int user_id, String newName, String newFullName, String login,
                                  Integer newPostCode, Integer age, String newMail, Boolean isAuthor, Boolean isModerator) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("UPDATE User SET fullName =: newFullName, login =: login," +
                    " age =: age,  isAuthor =: isAuthor,isModerator =: isModerator, email =: newMail WHERE id =: userId");
            if(!checkUserExisting(newMail)) {
                query.setParameter("userId", user_id);
                query.setParameter("newFullName",newFullName);
                query.setParameter("isModerator", isModerator);
                query.setParameter("isAuthor", isAuthor);
                query.setParameter("login", login);
                query.setParameter("age", age);
                query.setParameter("newMail", newMail);
                int rows = query.executeUpdate();
                session.getTransaction().commit();
                if(rows > 0){
                    System.out.println("User is successfully updated");
                }
            }else {
                System.out.println("User with same mail is already Exists");
            }
        }
    }
}
