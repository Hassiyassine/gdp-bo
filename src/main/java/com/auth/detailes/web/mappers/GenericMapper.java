package com.auth.detailes.web.mappers;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @author 	: Yassin OUICHOU
 * Email	: Ouichou.IT@gmail.com
 */
public abstract class GenericMapper<T, D> {

    public abstract T toEntity(D dto);
    public List<T> toEntities(List<D> listDTOS){
        return listDTOS.stream().map(d -> toEntity(d)).collect(Collectors.toList());
    };
    public abstract D toDTO(T entity);
    public List<D> toDTOS(List<T> listEntities){
        return listEntities.stream().map(d -> toDTO(d)).collect(Collectors.toList());
    };

}
