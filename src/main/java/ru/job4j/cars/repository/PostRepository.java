package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class PostRepository {
    private final CrudRepository crudRepository;

    /**
     * Список Post за текущую дату.
     * @return список объявлений.
     */
    public List<Post> findAllTodayPosts() {
        return crudRepository.query("from Post where created like " + "current_date()" + "%", Post.class);
    }

    /**
     * Список объявлений по car.name LIKE %key%
     * @param carName carName
     * @return список объявлений.
     */
    public List<Post> findByLikeLogin(String carName) {
        return crudRepository.query(
                "from Post post where post.car.name like :fKey", Post.class,
                Map.of("fKey", "%" + carName + "%")
        );
    }
}
