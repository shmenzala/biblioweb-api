/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.dto;

import java.util.Set;

/**
 *
 * @author shmen
 */
public class UsuariosDto {
    
    private String codigous;
    private String username;
    private String email;
    private String password;
    private Personas_perfilDto codigope;
    private Set<RolesDto> roles;
    private Set<LibrosDto> libros_favoritos;

    public UsuariosDto() {
    }

    public UsuariosDto(String codigous, String username, String email, String password, Personas_perfilDto codigope, Set<RolesDto> roles, Set<LibrosDto> libros_favoritos) {
        this.codigous = codigous;
        this.username = username;
        this.email = email;
        this.password = password;
        this.codigope = codigope;
        this.roles = roles;
        this.libros_favoritos = libros_favoritos;
    }

    public String getCodigous() {
        return codigous;
    }

    public void setCodigous(String codigous) {
        this.codigous = codigous;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Personas_perfilDto getCodigope() {
        return codigope;
    }

    public void setCodigope(Personas_perfilDto codigope) {
        this.codigope = codigope;
    }

    public Set<RolesDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolesDto> roles) {
        this.roles = roles;
    }

    public Set<LibrosDto> getLibros_favoritos() {
        return libros_favoritos;
    }

    public void setLibros_favoritos(Set<LibrosDto> libros_favoritos) {
        this.libros_favoritos = libros_favoritos;
    }
    
    
    
}
