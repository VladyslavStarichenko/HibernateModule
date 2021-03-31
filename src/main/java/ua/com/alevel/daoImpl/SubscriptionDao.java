package ua.com.alevel.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.com.alevel.dao.SubscriptionsDao;
import ua.com.alevel.model.User;
import ua.com.alevel.util.HibernateSessionFactoryUtil;

import java.util.List;

public class SubscriptionDao implements SubscriptionsDao {
    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    @Override
    public List<User> findAllSubscriptions(User subscriber) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createNativeQuery("SELECT u.login FROM users u JOIN user_subscriptions us ON u.id = us.account_id " +
                    "WHERE us.follower_id = ? AND us.account_id = u.id");
            query.setParameter(1, subscriber);
            List<User> resultList = query.getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }
}
