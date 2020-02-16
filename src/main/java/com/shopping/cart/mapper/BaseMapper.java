package com.shopping.cart.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Murat Karagözgil
 */

public abstract class BaseMapper<Entity, Dto> {

    public abstract Entity convertToEntity(final Dto dto);

    public abstract Dto convertToDTO(final Entity entity);

    public List<Dto> toDtoList(Iterable<Entity> entityList) {
        List<Dto> dtoList = new ArrayList<>();
        for (Entity entity : entityList) {
            dtoList.add(convertToDTO(entity));
        }

        return dtoList;
    }

    public Set<Dto> convertToDTOSet(Iterable<Entity> entityList) {
        return new HashSet<Dto>(toDtoList(entityList));
    }

    public List<Entity> convertToEntityList(Iterable<Dto> dtoList) {
        List<Entity> entityList = new ArrayList<>();
        for (Dto dto : dtoList) {
            entityList.add(convertToEntity(dto));
        }

        return entityList;
    }

    public Set<Entity> convertToEntitySet(Iterable<Dto> dtoList) {
        return new HashSet<>(convertToEntityList(dtoList));
    }
}
