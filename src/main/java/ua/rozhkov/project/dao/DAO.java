package ua.rozhkov.project.dao;

import java.util.List;

public interface DAO<T> {
    /**
     * Create new entity
     *
     * @param newEntity new entity to save
     * @return true, if operation successfully
     */
    boolean create(T newEntity);

    /**
     * Get entity with specified id
     *
     * @param idEntity entity's id
     * @return instance of entity
     */
    T get(long idEntity);

    /**
     * Get all entities
     *
     * @return list with all available entities
     */
    List<T> getAll();

    /**
     * Update entity wit specified id from specified entity
     *
     * @param idEntity      entity's-to-updated id
     * @param updatedEntity entity with new values
     * @return true, if operation successfully
     */
    boolean update(long idEntity, T updatedEntity);

    /**
     * Delete specified entity
     *
     * @param idEntity entity's-to-delete id
     * @return true, if operation successfully
     */
    boolean delete(long idEntity);
}
