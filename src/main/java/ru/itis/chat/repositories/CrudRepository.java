package ru.itis.chat.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<V, ID> {

    Optional<V> find(ID id);

    List<V> findAll();

    void save(V entity);

    void delete(ID id);

    void update(V entity);
}

