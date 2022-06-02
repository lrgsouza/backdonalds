package cdg.inatel.br.dao;

import java.util.List;

public interface BaseDao<T> {
    T get(Long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
}
