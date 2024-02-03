package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final static String CREATE_USERS_QUERY =
            "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(25), last_name VARCHAR(25), age SMALLINT)";


    public UserDaoHibernateImpl() {
//         sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_USERS_QUERY)
                    .executeUpdate();
            transaction.commit();
            System.out.println("ОК");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setName("John");
            user.setLastName("Doe");
            user.setAge((byte) 25);
            session.save(user);
            transaction.commit();
            System.out.println("ОК");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = null;
        SessionFactory sessionFactory = Util.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            usersList = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ОШИБКА! ");
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {

    }
}
