create table if not exists auto_post
(
    id              serial primary key,
    description     varchar not null,
    created         TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    auto_user_id    int references auto_user(id),
    car_id          int unique references car(id)
);