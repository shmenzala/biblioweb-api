/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.RolesDto;
import com.pe.sh.Biblioapi.exceptions.ResourceNotFoundException;
import com.pe.sh.Biblioapi.model.Roles;
import com.pe.sh.Biblioapi.repository.RolesRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class RolesServiceImpl extends Mapper<Roles, RolesDto> implements RolesService {

    private RolesRepository rolesRepository;

    public RolesServiceImpl(RolesRepository rolesRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.rolesRepository = rolesRepository;
    }

    @Override
    public RolesDto create(RolesDto dto) {
        Roles roles = toEntity(dto, Roles.class);
        Roles nuevoRol = rolesRepository.save(roles);

        return toDto(nuevoRol, RolesDto.class);
    }

    @Override
    public List<RolesDto> findAll() {
        List<Roles> roles = rolesRepository.findAll();

        return roles.stream().map(rol -> toDto(rol, RolesDto.class)).collect(Collectors.toList());
    }

    @Override
    public RolesDto findById(String id) {
        Roles roles = rolesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));

        return toDto(roles, RolesDto.class);
    }

    @Override
    public RolesDto update(RolesDto dto, String id) {
        Roles roles = rolesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));

        roles.setNombre(dto.getNombre());

        Roles actualizaRoles = rolesRepository.save(roles);

        return toDto(actualizaRoles, RolesDto.class);
    }

    @Override
    public void delete(String id) {
        Roles roles = rolesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
        rolesRepository.delete(roles);
    }

}
