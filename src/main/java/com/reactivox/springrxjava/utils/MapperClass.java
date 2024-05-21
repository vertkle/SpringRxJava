package com.reactivox.springrxjava.utils;

import com.reactivox.springrxjava.config.ModelMapperConfig;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperClass<E, D> {
    private final ModelMapper modelMapper;
    public D entityToDto(E entity, Class<D>dto){
        return modelMapper.map(entity, dto);
    }
    public E dtoToEntity(D dto, Class<E>entity){
        return modelMapper.map(dto, entity);
    }
}
