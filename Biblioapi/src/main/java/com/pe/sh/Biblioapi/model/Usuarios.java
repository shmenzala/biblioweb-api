/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author shmen
 */
@Entity
@Table(name = "USUARIOS")
public class Usuarios implements Serializable{
    
    @Id
    @Column(name = "codigous")
    private String id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "codigope")
    private Personas_perfil personas_perfil;

    public Usuarios() {
    }

    public Usuarios(String id, String username, String email, String password, Personas_perfil personas_perfil) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.personas_perfil = personas_perfil;
    }

    public String getCodigous() {
        return id;
    }

    public void setCodigous(String id) {
        this.id = id;
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

    public Personas_perfil getPersonas_perfil() {
        return personas_perfil;
    }

    public void setPersonas_perfil(Personas_perfil personas_perfil) {
        this.personas_perfil = personas_perfil;
    }
    
}
