package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Owner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class OwnerRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param owner владелец.
     * @return владелец с id.
     */
    public Owner create(Owner owner) {
        crudRepository.run(session -> session.persist(owner));
        return owner;
    }

    /**
     * Обновить в базе владельца.
     * @param owner владелец.
     */
    public void update(Owner owner) {
        crudRepository.run(session -> session.merge(owner));
    }

    /**
     * Удалить владельца по id.
     * @param ownerId ID
     */
    public void delete(int ownerId) {
        crudRepository.run(
                "delete from Owner where id = :fId",
                Map.of("fId", ownerId)
        );
    }

    /**
     * Список Owner отсортированных по id.
     * @return список владельцев.
     */
    public List<Owner> findAllOrderById() {
        return crudRepository.query("from Owner order by id asc", Owner.class);
    }

    /**
     * Найти владельца по ID
     * @return владелец.
     */
    public Optional<Owner> findById(int ownerId) {
        return crudRepository.optional(
                "from Owner where id = :fId", Owner.class,
                Map.of("fId", ownerId)
        );
    }

    /**
     * Список владельцев по name LIKE %key%
     * @param key key
     * @return список владельцев.
     */
    public List<Owner> findByLikeLogin(String key) {
        return crudRepository.query(
                "from Owner where name like :fKey", Owner.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Найти владельцев по name.
     * @param name name.
     * @return Optional or name.
     */
    public Optional<Owner> findByLogin(String name) {
        return crudRepository.optional(
                "from Owner where name = :fName", Owner.class,
                Map.of("fName", name)
        );
    }
}
