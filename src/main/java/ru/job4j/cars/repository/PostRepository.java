package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Post;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@AllArgsConstructor
public class PostRepository {
    private final CrudRepository crudRepository;

    /**
     * Список Post за текущую дату.
     * @return список объявлений.
     */
    public List<Post> findAllTodayPosts() {
        LocalDateTime referenceDateTime = now().minusDays(1).truncatedTo(ChronoUnit.DAYS);
        Timestamp referenceDate = Timestamp.valueOf(referenceDateTime);
        return crudRepository.query(
                "from Post post where created >= :fKey", Post.class,
                Map.of("fKey", referenceDate)
        );
    }

    /**
     * Список объявлений по car.name LIKE %key%
     * @param carName carName
     * @return список объявлений.
     */
    public List<Post> findByCarName(String carName) {
        return crudRepository.query(
                "from Post post where post.car.name = :fKey", Post.class,
                Map.of("fKey", carName)
        );
    }

    /**
     * Список объявлений с фото
     * @return список объявлений.
     */
    public List<Post> findAllPostsWithPhoto() {
        return crudRepository.query("from Post where filePath is not null", Post.class);
    }
}
