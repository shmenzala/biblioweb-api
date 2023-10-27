/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.Personas_perfilDto;
import com.pe.sh.Biblioapi.exceptions.BiblioapiAppException;
import com.pe.sh.Biblioapi.exceptions.ResourceNotFoundException;
import com.pe.sh.Biblioapi.model.Personas_perfil;
import com.pe.sh.Biblioapi.model.Usuarios;
import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import com.pe.sh.Biblioapi.repository.Personas_perfilRepository;
import com.pe.sh.Biblioapi.repository.UsuariosRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class Personas_perfilServiceImpl extends Mapper<Personas_perfil, Personas_perfilDto> implements Personas_perfilService {

    private final Personas_perfilRepository personas_perfilRepository;
    private final UsuariosRepository usuariosRepository;

    public Personas_perfilServiceImpl(Personas_perfilRepository personas_perfilRepository, UsuariosRepository usuariosRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.personas_perfilRepository = personas_perfilRepository;
        this.usuariosRepository = usuariosRepository;
    }

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
        Personas_perfil personas_perfil = personas_perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona_perfil", "id", id));

        return toDto(personas_perfil, Personas_perfilDto.class);
    }

    @Override
    public Personas_perfilDto update(Personas_perfilDto dto, String codigous) {
        Usuarios usuarios = usuariosRepository.findById(codigous)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", codigous));

        if (!usuariosRepository.existsByUsername(usuarios.getUsername())) {
            throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Ese nombre de usuario no existe");
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!(principal instanceof UserDetails)) {
                throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Usuario no proveniente del sistema");
            } else {
                String username = ((UserDetails) principal).getUsername();
                System.out.println("USUARIO LOGUEADO: " + username);
                if (!username.equals(usuarios.getUsername())) {
                    throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Acceso denegado, no puedes modificar a este usuario");
                }
            }
        }

        final String personaId = usuarios.getPersonas_perfil().getCodigope();

        Personas_perfil personas_perfil = personas_perfilRepository.findById(personaId)
                .orElseThrow(() -> new ResourceNotFoundException("Persona_perfil", "id", personaId));

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
        Personas_perfil personas_perfil = personas_perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona_perfil", "id", id));
        personas_perfilRepository.delete(personas_perfil);
    }

    @Override
    public PageableDataDto findAllPagination(int pageNo, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Personas_perfil> ppfPage = personas_perfilRepository.findAll(pageable);

        List<Personas_perfilDto> content = ppfPage.getContent().stream().map(ppf -> toDto(ppf, Personas_perfilDto.class)).collect(Collectors.toList());

        PageableDataDto ppfResp = new PageableDataDto();

        ppfResp.setContent(content);
        ppfResp.setPageNo(ppfPage.getNumber());
        ppfResp.setPageSize(ppfPage.getSize());
        ppfResp.setTotalElements(ppfPage.getTotalElements());
        ppfResp.setTotalPages(ppfPage.getTotalPages());
        ppfResp.setLast(ppfPage.isLast());

        return ppfResp;
    }

    @Override
    public PageableDataDto findAllWithCoincidence(String search, int pageNo, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Personas_perfil> ppfPage = personas_perfilRepository.buscarPersonas_perfilPorCoincidencia(search, pageable);

        List<Personas_perfilDto> content = ppfPage.getContent().stream().map(ppf -> toDto(ppf, Personas_perfilDto.class)).collect(Collectors.toList());

        PageableDataDto ppfResp = new PageableDataDto();

        ppfResp.setContent(content);
        ppfResp.setPageNo(ppfPage.getNumber());
        ppfResp.setPageSize(ppfPage.getSize());
        ppfResp.setTotalElements(ppfPage.getTotalElements());
        ppfResp.setTotalPages(ppfPage.getTotalPages());
        ppfResp.setLast(ppfPage.isLast());

        return ppfResp;
    }

}
