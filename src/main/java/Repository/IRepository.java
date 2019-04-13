package Repository;

import Domain.Entity;

import java.util.List;

public interface IRepository<T extends Entity> {

    /**
     * returns a entity by id
     * @param id of the entity
     * @return a entity
     */
    T getById(String id);

    /**
     * adds a movie
     * @param entity to add
     * @throws RuntimeException if a entity with that id already exists
     */
    void insert(T entity);

    /**
     * updates a movie
     * @param entity to update
     * @throws RuntimeException if a movie with that id doesn't exists
     */
    void update(T entity);

    /**
     * removes a movie
     * @param id of the movie we want to remove
     * @throws RuntimeException if a movie with that id doesn't exists
     */
    void remove(String id);

    /**
     * @return a list with all entities
     */
    List<T> getAll();

}
