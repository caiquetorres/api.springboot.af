package com.app.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

public abstract class BaseRepository<TEntity extends BaseEntity, TCreateDTO extends ToEntity<TEntity>, TUpdateDTO extends ModifyEntity<TEntity>> {
    private int currentId;
    private List<TEntity> entities;

    public BaseRepository() {
    }

    @PostConstruct
    public void initialize() {
        currentId = 0;
        entities = new ArrayList<TEntity>();
    }

    public void save(TEntity entity) {
        entity.setId(++currentId);
        entities.add(entity);
    }

    public TEntity findOne(int id) {
        List<TEntity> entities = findAll(entity -> entity.getId() == id);
        if (entities.size() == 0)
            return null;
        return entities.get(0);
    }

    public List<TEntity> findAll(Predicate<TEntity> predicate) {
        return entities.stream().filter(predicate).collect(Collectors.toList());
    }

    public void delete(int id) {
        entities.removeIf(entity -> entity.id == (id));
    }

    public void update(int id, TUpdateDTO updateDto) {
        updateDto.modifyEntity(findOne(id));
    }

    public boolean contains(int id) {
        return entities.stream().anyMatch(entity -> entity.id == (id));
    }
}
