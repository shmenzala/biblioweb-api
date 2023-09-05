/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.UsuariosDto;
import com.pe.sh.Biblioapi.model.Libros;
import com.pe.sh.Biblioapi.model.Personas_perfil;
import com.pe.sh.Biblioapi.model.Roles;
import com.pe.sh.Biblioapi.model.Usuarios;
import com.pe.sh.Biblioapi.repository.LibrosRepository;
import com.pe.sh.Biblioapi.repository.Personas_perfilRepository;
import com.pe.sh.Biblioapi.repository.RolesRepository;
import com.pe.sh.Biblioapi.repository.UsuariosRepository;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class UsuariosServiceImpl extends Mapper<Usuarios, UsuariosDto> implements UsuariosService {
    
    @Autowired
    private UsuariosRepository usuariosRepository;
    
    @Autowired
    private Personas_perfilRepository personas_perfilRepository;
    
    @Autowired
    private RolesRepository rolesRepository;
    
    @Autowired
    private LibrosRepository librosRepository;

    @Override
    public UsuariosDto create(UsuariosDto dto, String codigorol) {
        Usuarios usuarios = toEntity(dto, Usuarios.class);
        
        Roles rol = rolesRepository.findById(codigorol).orElseThrow(null);
        
        usuarios.setUsername(dto.getUsername());
        usuarios.setEmail(dto.getEmail());
        usuarios.setPassword(dto.getPassword());
        usuarios.setRoles(Collections.singleton(rol));
        
        Personas_perfil personas_perfil = new Personas_perfil();
        personas_perfil.setNombres(usuarios.getUsername());
        
        Personas_perfil nuevaPersona_perfil = personas_perfilRepository.save(personas_perfil);
        
        usuarios.setPersonas_perfil(nuevaPersona_perfil);
        
        Usuarios nuevoUsuario = usuariosRepository.save(usuarios);
        
        return toDto(nuevoUsuario, UsuariosDto.class);
    }

    @Override
    public List<UsuariosDto> findAll() {
        List<Usuarios> usuarios = usuariosRepository.findAll();
        
        return usuarios.stream().map(usuario -> toDto(usuario, UsuariosDto.class)).collect(Collectors.toList());
    }

    @Override
    public UsuariosDto findById(String id) {
        Usuarios usuarios = usuariosRepository.findById(id).orElseThrow(null);
        
        return toDto(usuarios, UsuariosDto.class);
    }

    
    @Override
    public UsuariosDto update(UsuariosDto dto, String id) {
        Usuarios usuarios = usuariosRepository.findById(id).orElseThrow(null);
        
        usuarios.setUsername(dto.getUsername());
        usuarios.setEmail(dto.getEmail());
        usuarios.setPassword(dto.getPassword());
        
        Usuarios actualizaUsuario = usuariosRepository.save(usuarios);
        
        return toDto(actualizaUsuario, UsuariosDto.class);
    }

    @Override
    public void delete(String id) {
        Usuarios usuarios = usuariosRepository.findById(id).orElseThrow(null);
        usuariosRepository.delete(usuarios);
    }

    @Override
    public UsuariosDto addRoles(String id, String codigorol) {
        Usuarios usuario = usuariosRepository.findById(id).orElseThrow(null);
        Roles rol = rolesRepository.findById(codigorol).orElseThrow(null);                
        
        Set<Roles> rolesDeUsuarioActual = usuario.getRoles();
        rolesDeUsuarioActual.add(rol);
        
        usuario.setRoles(rolesDeUsuarioActual);
        
        Usuarios actualizaUsuario = usuariosRepository.save(usuario);
        
        return toDto(actualizaUsuario, UsuariosDto.class);
    }

    @Override
    public UsuariosDto removeRoles(String id, String codigorol) {
        Usuarios usuario = usuariosRepository.findById(id).orElseThrow(null);
        Roles rol = rolesRepository.findById(codigorol).orElseThrow(null);                
        
        Set<Roles> rolesDeUsuarioActual = usuario.getRoles();
        rolesDeUsuarioActual.remove(rol);
        
        usuario.setRoles(rolesDeUsuarioActual);
        
        Usuarios actualizaUsuario = usuariosRepository.save(usuario);
        
        return toDto(actualizaUsuario, UsuariosDto.class);
    }

    @Override
    public UsuariosDto addLibrosFavoritos(String id, String codigolib) {
        Usuarios usuario = usuariosRepository.findById(id).orElseThrow(null);
        Libros libro = librosRepository.findById(codigolib).orElseThrow(null);                
        
        Set<Libros> librosFavDeUsuarioActual = usuario.getLibros();
        librosFavDeUsuarioActual.add(libro);
        
        usuario.setLibros(librosFavDeUsuarioActual);
        
        Usuarios actualizaUsuario = usuariosRepository.save(usuario);
        
        return toDto(actualizaUsuario, UsuariosDto.class);
    }

    @Override
    public UsuariosDto removeLibrosFavoritos(String id, String codigolib) {
        Usuarios usuario = usuariosRepository.findById(id).orElseThrow(null);
        Libros libro = librosRepository.findById(codigolib).orElseThrow(null);                
        
        Set<Libros> librosFavDeUsuarioActual = usuario.getLibros();
        librosFavDeUsuarioActual.remove(libro);
        
        usuario.setLibros(librosFavDeUsuarioActual);
        
        Usuarios actualizaUsuario = usuariosRepository.save(usuario);
        
        return toDto(actualizaUsuario, UsuariosDto.class);
    }

    
    @Override
    public UsuariosDto create(UsuariosDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
