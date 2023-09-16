/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.configuration;

import org.modelmapper.ModelMapper;

/**
 *
 * @author shmen
 * @param <E>
 * @param <T>
 */
public class Mapper<E, T> {

    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected E toEntity(T dto, Class<E> clase) {
        E entity = modelMapper.map(dto, clase);
        return entity;
    }

    protected T toDto(E entity, Class<T> clase) {
        T dto = modelMapper.map(entity, clase);
        return dto;
    }

}
