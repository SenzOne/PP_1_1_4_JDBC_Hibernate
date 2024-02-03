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
    private final static String DROP_USERS_TABLE_QUERY = "DROP TABLE IF EXISTS users";
    private final static String CLEAN_USERS_TABLE_QUERY = "TRUNCATE TABLE users";

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }

    private void sqlFunction(String sql) {
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql)
                    .executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUsersTable() {
        sqlFunction(CREATE_USERS_QUERY);
    }

    @Override
    public void dropUsersTable() {
        sqlFunction(DROP_USERS_TABLE_QUERY);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
            System.out.println("ОК");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            usersList = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        sqlFunction(CLEAN_USERS_TABLE_QUERY);
    }
}
