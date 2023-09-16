/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.dto.JwtAuthResponseDto_;
import com.pe.sh.Biblioapi.dto.LoginDto_;
import com.pe.sh.Biblioapi.dto.RegisterDto_;
import com.pe.sh.Biblioapi.exceptions.BiblioapiAppException;
import com.pe.sh.Biblioapi.model.Personas_perfil;
import com.pe.sh.Biblioapi.model.Roles;
import com.pe.sh.Biblioapi.model.Usuarios;
import com.pe.sh.Biblioapi.repository.Personas_perfilRepository;
import com.pe.sh.Biblioapi.repository.RolesRepository;
import com.pe.sh.Biblioapi.repository.UsuariosRepository;
import com.pe.sh.Biblioapi.security.JwtTokenProvider;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuariosRepository usuariosRepository;

    private final RolesRepository rolesRepository;

    private final Personas_perfilRepository personas_perfilRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UsuariosRepository usuariosRepository, RolesRepository rolesRepository, Personas_perfilRepository personas_perfilRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.usuariosRepository = usuariosRepository;
        this.rolesRepository = rolesRepository;
        this.personas_perfilRepository = personas_perfilRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtAuthResponseDto_ register(RegisterDto_ regDto) {
        if (usuariosRepository.existsByUsername(regDto.getUsername())) {
            throw new BiblioapiAppException(HttpStatus.BAD_REQUEST, "Ese nombre de usuario ya existe");
        }

        Usuarios usuario = new Usuarios();
        usuario.setUsername(regDto.getUsername());
        usuario.setEmail(regDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(regDto.getPassword()));

        Roles rol = rolesRepository.findByNombre("ROLE_USER").get();
        usuario.setRoles(Collections.singleton(rol));

        Personas_perfil persona_perfil = new Personas_perfil();
        persona_perfil.setNombres(usuario.getUsername());

        Personas_perfil nuevaPersona_perfil = personas_perfilRepository.save(persona_perfil);

        usuario.setPersonas_perfil(nuevaPersona_perfil);

        usuariosRepository.save(usuario);

        String token = jwtTokenProvider.generateToken(usuario);

        return new JwtAuthResponseDto_(token);
    }

    @Override
    public JwtAuthResponseDto_ authenticate(LoginDto_ logDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        logDto.getUsername(),
                        logDto.getPassword()
                )
        );

        Usuarios usuario = usuariosRepository.findByUsername(logDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        usuario.getRoles().forEach((rol) -> System.out.println(rol.getNombre()));
        System.out.println("AUTORIDADES DEL USUARIO ACTUAL: " + usuario.getAuthorities());

        String token = jwtTokenProvider.generateToken(usuario);
        return new JwtAuthResponseDto_(token);
    }

}
