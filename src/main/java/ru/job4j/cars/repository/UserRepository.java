package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try (session) {
            Transaction currentTransaction = session.beginTransaction();
            session.save(user);
            currentTransaction.commit();
            return user;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try (session) {
            Transaction currentTransaction = session.beginTransaction();
            session.update(user);
            currentTransaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try (session) {
            Transaction currentTransaction = session.beginTransaction();
            User user = new User();
            user.setId(userId);
            session.delete(user);
            currentTransaction.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        try (session) {
            Transaction currentTransaction = session.beginTransaction();
            List<User> result = session.createQuery("FROM User ORDER BY id DESC", User.class).list();
            currentTransaction.commit();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        try (session) {
            Transaction currentTransaction = session.beginTransaction();
            User result = session.get(User.class, userId);
            currentTransaction.commit();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        try (session) {
            Transaction currentTransaction = session.beginTransaction();
            List<User> resultList;
            Query<User> query = session.createQuery("FROM User WHERE login LIKE :key", User.class);
            query.setParameter("key", "%" + key + "%");
            resultList = new ArrayList<>(query.list());
            currentTransaction.commit();
            return resultList;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        try (session) {
            Transaction currentTransaction = session.beginTransaction();
            Optional<User> result = session.createQuery("FROM User WHERE login = :fLogin", User.class)
                    .setParameter("fLogin", login)
                    .uniqueResultOptional();
            currentTransaction.commit();
            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}