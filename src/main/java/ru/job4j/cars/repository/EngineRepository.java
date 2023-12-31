package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class EngineRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param engine двигатель.
     * @return двигатель с id.
     */
    public Engine create(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
        return engine;
    }

    /**
     * Обновить в базе двигатель.
     * @param engine двигатель.
     */
    public void update(Engine engine) {
        crudRepository.run(session -> session.merge(engine));
    }

    /**
     * Удалить двигатель по id.
     * @param engineId ID
     */
    public void delete(int engineId) {
        crudRepository.run(
                "delete from Engine where id = :fId",
                Map.of("fId", engineId)
        );
    }

    /**
     * Список Engine отсортированных по id.
     * @return список двигателей.
     */
    public List<Engine> findAllOrderById() {
        return crudRepository.query("from Engine order by id asc", Engine.class);
    }

    /**
     * Найти двигатель по ID
     * @return двитатель.
     */
    public Optional<Engine> findById(int engineId) {
        return crudRepository.optional(
                "from Engine where id = :fId", Engine.class,
                Map.of("fId", engineId)
        );
    }

    /**
     * Список двигателей по name LIKE %key%
     * @param key key
     * @return список двигателей.
     */
    public List<Engine> findByLikeLogin(String key) {
        return crudRepository.query(
                "from Engine where name like :fKey", Engine.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Найти двигатели по name.
     * @param name name.
     * @return Optional or name.
     */
    public Optional<Engine> findByLogin(String name) {
        return crudRepository.optional(
                "from Engine where name = :fName", Engine.class,
                Map.of("fName", name)
        );
    }
}
