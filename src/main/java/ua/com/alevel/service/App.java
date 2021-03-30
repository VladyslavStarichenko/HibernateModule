package ua.com.alevel.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.model.User;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        List<User> userFollowersList = new ArrayList<>();
        List<User> userSubscribersList = new ArrayList<>();
        List<User> userFollowersList1 = new ArrayList<>();
        List<User> userSubscribersList1 = new ArrayList<>();
        User user1 = new User();
        user1.setAge(20);
        user1.setEmail("vlstar21@uk.net");
        user1.setAuthor(true);
        user1.setFullName("MappingTest");
        user1.setLogin("grassbomb1374");
        user1.setModerator(true);
        user1.setFollowers(userFollowersList);
        user1.setSubscriptions(userSubscribersList);
        UserDao.userCreation(user1);
        userSubscribersList1.add(user1);

        User user = new User();
        user.setAge(18);
        user.setEmail("vlstar09@uk.net");
        user.setAuthor(true);
        user.setFullName("Vladyslav Test");
        user.setLogin("goldsilver12");
        user.setModerator(true);
        user.setFollowers(userFollowersList1);
        user.setSubscriptions(userSubscribersList1);


        UserDao.userCreation(user);
//        entityManager.persist(user);
//        entityManager.flush();
    }


}
