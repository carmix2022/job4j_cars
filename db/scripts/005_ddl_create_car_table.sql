create table car(
    id                  serial primary key,
    name                varchar not null,
    engine_id           int not null unique references engine(id),
    current_owner_id    int not null references owners(id)
);