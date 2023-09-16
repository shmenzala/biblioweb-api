/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.GenerosDto;
import com.pe.sh.Biblioapi.exceptions.ResourceNotFoundException;
import com.pe.sh.Biblioapi.model.Generos;
import com.pe.sh.Biblioapi.repository.GenerosRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class GenerosServiceImpl extends Mapper<Generos, GenerosDto> implements GenerosService {

    private final GenerosRepository generosRepository;

    public GenerosServiceImpl(GenerosRepository generosRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.generosRepository = generosRepository;
    }

    @Override
    public GenerosDto create(GenerosDto dto) {
        Generos generos = toEntity(dto, Generos.class);
        Generos nuevoGenero = generosRepository.save(generos);

        return toDto(nuevoGenero, GenerosDto.class);
    }

    @Override
    public List<GenerosDto> findAll() {
        List<Generos> generos = generosRepository.findAll();

        return generos.stream().map(genero -> toDto(genero, GenerosDto.class)).collect(Collectors.toList());
    }

    @Override
    public GenerosDto findById(String id) {
        Generos generos = generosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero", "id", id));

        return toDto(generos, GenerosDto.class);
    }

    @Override
    public GenerosDto update(GenerosDto dto, String id) {
        Generos generos = generosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero", "id", id));

        generos.setNombre(dto.getNombre());

        Generos actualizaGenero = generosRepository.save(generos);

        return toDto(actualizaGenero, GenerosDto.class);

    }

    @Override
    public void delete(String id) {
        Generos generos = generosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genero", "id", id));
        generosRepository.delete(generos);
    }

}
