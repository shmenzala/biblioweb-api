/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.AutoresDto;
import com.pe.sh.Biblioapi.exceptions.ResourceNotFoundException;
import com.pe.sh.Biblioapi.model.Autores;
import com.pe.sh.Biblioapi.repository.AutoresRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class AutoresServiceImpl extends Mapper<Autores, AutoresDto> implements AutoresService {

    @Autowired
    private AutoresRepository autoresRepository;

    @Override
    public AutoresDto create(AutoresDto dto) {
        Autores autores = toEntity(dto, Autores.class);
        Autores nuevoAutor = autoresRepository.save(autores);

        return toDto(nuevoAutor, AutoresDto.class);
    }

    @Override
    public List<AutoresDto> findAll() {
        List<Autores> autores = autoresRepository.findAll();

        return autores.stream().map(autor -> toDto(autor, AutoresDto.class)).collect(Collectors.toList());
    }

    @Override
    public AutoresDto findById(String id) {
        Autores autores = autoresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor", "id", id));

        return toDto(autores, AutoresDto.class);
    }

    @Override
    public AutoresDto update(AutoresDto dto, String id) {
        Autores autores = autoresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor", "id", id));

        autores.setNombres(dto.getNombres());
        autores.setApellidos(dto.getApellidos());
        autores.setFech_nacimiento(dto.getFech_nacimiento());
        autores.setFotografia(dto.getFotografia());
        autores.setExtra_info(dto.getExtra_info());

        Autores actualizarAutor = autoresRepository.save(autores);

        return toDto(actualizarAutor, AutoresDto.class);
    }

    @Override
    public void delete(String id) {
        Autores autores = autoresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor", "id", id));
        autoresRepository.delete(autores);
    }

}
