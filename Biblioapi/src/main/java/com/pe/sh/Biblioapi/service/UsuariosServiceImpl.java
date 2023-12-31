/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.UsuariosDto;
import com.pe.sh.Biblioapi.exceptions.BiblioapiAppException;
import com.pe.sh.Biblioapi.exceptions.ResourceNotFoundException;
import com.pe.sh.Biblioapi.model.Libros;
import com.pe.sh.Biblioapi.model.Personas_perfil;
import com.pe.sh.Biblioapi.model.Roles;
import com.pe.sh.Biblioapi.model.Usuarios;
import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import com.pe.sh.Biblioapi.repository.LibrosRepository;
import com.pe.sh.Biblioapi.repository.Personas_perfilRepository;
import com.pe.sh.Biblioapi.repository.RolesRepository;
import com.pe.sh.Biblioapi.repository.UsuariosRepository;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
public class UsuariosServiceImpl extends Mapper<Usuarios, UsuariosDto> implements UsuariosService {

    private final UsuariosRepository usuariosRepository;

    private final Personas_perfilRepository personas_perfilRepository;

    private final RolesRepository rolesRepository;

    private final LibrosRepository librosRepository;

    public UsuariosServiceImpl(UsuariosRepository usuariosRepository, Personas_perfilRepository personas_perfilRepository, RolesRepository rolesRepository, LibrosRepository librosRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.usuariosRepository = usuariosRepository;
        this.personas_perfilRepository = personas_perfilRepository;
        this.rolesRepository = rolesRepository;
        this.librosRepository = librosRepository;
    }

    @Override
    public UsuariosDto create(UsuariosDto dto, String codigorol) {
        Usuarios usuarios = toEntity(dto, Usuarios.class);

        Roles rol = rolesRepository.findById(codigorol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", codigorol));

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
    public PageableDataDto findAllPagination(int pageNo, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Usuarios> usuariosPage = usuariosRepository.findAll(pageable);

        List<UsuariosDto> content = usuariosPage.getContent().stream().map(usuario -> toDto(usuario, UsuariosDto.class)).collect(Collectors.toList());

        PageableDataDto usuariosResp = new PageableDataDto();

        usuariosResp.setContent(content);
        usuariosResp.setPageNo(usuariosPage.getNumber());
        usuariosResp.setPageSize(usuariosPage.getSize());
        usuariosResp.setTotalElements(usuariosPage.getTotalElements());
        usuariosResp.setTotalPages(usuariosPage.getTotalPages());
        usuariosResp.setLast(usuariosPage.isLast());

        return usuariosResp;
    }

    @Override
    public List<UsuariosDto> findAll() {
        List<Usuarios> usuarios = usuariosRepository.findAll();

        return usuarios.stream().map(usuario -> toDto(usuario, UsuariosDto.class)).collect(Collectors.toList());
    }

    @Override
    public UsuariosDto findById(String id) {
        Usuarios usuarios = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        return toDto(usuarios, UsuariosDto.class);
    }

    @Override
    public UsuariosDto update(UsuariosDto dto, String id) {
        Usuarios usuarios = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        usuarios.setUsername(dto.getUsername());
        usuarios.setEmail(dto.getEmail());
        usuarios.setPassword(dto.getPassword());

        Usuarios actualizaUsuario = usuariosRepository.save(usuarios);

        return toDto(actualizaUsuario, UsuariosDto.class);
    }

    @Override
    public void delete(String id) {
        Usuarios usuarios = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        usuariosRepository.delete(usuarios);
    }

    @Override
    public UsuariosDto addRoles(String id, String codigorol) {
        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        Roles rol = rolesRepository.findById(codigorol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", codigorol));

        Set<Roles> rolesDeUsuarioActual = usuario.getRoles();
        rolesDeUsuarioActual.add(rol);

        usuario.setRoles(rolesDeUsuarioActual);

        Usuarios actualizaUsuario = usuariosRepository.save(usuario);

        return toDto(actualizaUsuario, UsuariosDto.class);
    }

    @Override
    public UsuariosDto removeRoles(String id, String codigorol) {
        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        Roles rol = rolesRepository.findById(codigorol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", codigorol));

        Set<Roles> rolesDeUsuarioActual = usuario.getRoles();
        rolesDeUsuarioActual.remove(rol);

        usuario.setRoles(rolesDeUsuarioActual);

        Usuarios actualizaUsuario = usuariosRepository.save(usuario);

        return toDto(actualizaUsuario, UsuariosDto.class);
    }

    @Override
    public UsuariosDto addLibrosFavoritos(String id, String codigolib) {
        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        if (!usuariosRepository.existsByUsername(usuario.getUsername())) {
            throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Ese nombre de usuario no existe");
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!(principal instanceof UserDetails)) {
                throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Usuario no proveniente del sistema");
            } else {
                String username = ((UserDetails) principal).getUsername();
                System.out.println("USUARIO LOGUEADO: " + username);
                if (!username.equals(usuario.getUsername())) {
                    throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Acceso denegado, no puedes modificar a este usuario");
                }
            }
        }

        Libros libro = librosRepository.findById(codigolib)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", codigolib));

        Set<Libros> librosFavDeUsuarioActual = usuario.getLibros();
        librosFavDeUsuarioActual.add(libro);

        usuario.setLibros(librosFavDeUsuarioActual);

        Usuarios actualizaUsuario = usuariosRepository.save(usuario);

        return toDto(actualizaUsuario, UsuariosDto.class);
    }

    @Override
    public UsuariosDto removeLibrosFavoritos(String id, String codigolib) {
        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        
        if (!usuariosRepository.existsByUsername(usuario.getUsername())) {
            throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Ese nombre de usuario no existe");
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!(principal instanceof UserDetails)) {
                throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Usuario no proveniente del sistema");
            } else {
                String username = ((UserDetails) principal).getUsername();
                System.out.println("USUARIO LOGUEADO: " + username);
                if (!username.equals(usuario.getUsername())) {
                    throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Acceso denegado, no puedes modificar a este usuario");
                }
            }
        }
        
        Libros libro = librosRepository.findById(codigolib)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", codigolib));

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

    @Override
    public PageableDataDto findAllWithCoincidence(String search, int pageNo, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Usuarios> usuariosPage = usuariosRepository.buscarUsuariosPorCoincidencia(search, pageable);

        List<UsuariosDto> content = usuariosPage.getContent().stream().map(usuario -> toDto(usuario, UsuariosDto.class)).collect(Collectors.toList());

        PageableDataDto usuariosResp = new PageableDataDto();

        usuariosResp.setContent(content);
        usuariosResp.setPageNo(usuariosPage.getNumber());
        usuariosResp.setPageSize(usuariosPage.getSize());
        usuariosResp.setTotalElements(usuariosPage.getTotalElements());
        usuariosResp.setTotalPages(usuariosPage.getTotalPages());
        usuariosResp.setLast(usuariosPage.isLast());

        return usuariosResp;
    }

}
