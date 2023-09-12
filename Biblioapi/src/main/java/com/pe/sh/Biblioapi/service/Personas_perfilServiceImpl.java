/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.Personas_perfilDto;
import com.pe.sh.Biblioapi.model.Personas_perfil;
import com.pe.sh.Biblioapi.repository.Personas_perfilRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class Personas_perfilServiceImpl extends Mapper<Personas_perfil, Personas_perfilDto> implements Personas_perfilService{

    @Autowired
    private Personas_perfilRepository personas_perfilRepository;
    
    @Override
    public Personas_perfilDto create(Personas_perfilDto dto) {
        Personas_perfil personas_perfil = toEntity(dto, Personas_perfil.class);
        Personas_perfil nuevaPersona_perfil = personas_perfilRepository.save(personas_perfil);
        
        return toDto(nuevaPersona_perfil, Personas_perfilDto.class);        
    }

    @Override
    public List<Personas_perfilDto> findAll() {
        List<Personas_perfil> personas_perfil = personas_perfilRepository.findAll();
        
        return personas_perfil.stream().map(pp -> toDto(pp, Personas_perfilDto.class)).collect(Collectors.toList());
    }

    @Override
    public Personas_perfilDto findById(String id) {
        Personas_perfil personas_perfil = personas_perfilRepository.findById(id).orElseThrow(null);
        
        return toDto(personas_perfil, Personas_perfilDto.class);
    }

    @Override
    public Personas_perfilDto update(Personas_perfilDto dto, String id) {
        Personas_perfil personas_perfil = personas_perfilRepository.findById(id).orElseThrow(null);        
        
        personas_perfil.setNombres(dto.getNombres());
        personas_perfil.setApellidos(dto.getApellidos());
        personas_perfil.setGenero(dto.getGenero());
        personas_perfil.setFech_nacimiento(dto.getFech_nacimiento());
        personas_perfil.setFotografia(dto.getFotografia());
        
        Personas_perfil actualizaPersona_perfil = personas_perfilRepository.save(personas_perfil);
        
        return toDto(actualizaPersona_perfil, Personas_perfilDto.class);
    }

    @Override
    public void delete(String id) {
        Personas_perfil personas_perfil = personas_perfilRepository.findById(id).orElseThrow(null);
        personas_perfilRepository.delete(personas_perfil);
    }
    
    
    
}