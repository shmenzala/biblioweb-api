/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pe.sh.Biblioapi.configuration.StringKeyGenerator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author shmen
 */
@Entity
@Table(name = "USUARIOS")
public class Usuarios implements Serializable, UserDetails{
    
    @Id
    @Column(name = "codigous")
    @GeneratedValue(generator = "inc_seqUsu")
    @GenericGenerator(name = "inc_seqUsu", type = StringKeyGenerator.class,
            parameters = {@Parameter(name = "sqcName", value = "USUARIOS_SEQ"),
                          @Parameter(name = "identificator_id", value = "US")})
    @SequenceGenerator(name = "inc_seqUsu", sequenceName = "USUARIOS_SEQ", initialValue = 1, allocationSize = 1)
    private String id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "codigope")
    private Personas_perfil personas_perfil;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "USUARIOS_ROLES",
            joinColumns = @JoinColumn(name = "codigous", referencedColumnName = "codigous"),
            inverseJoinColumns = @JoinColumn(name = "codigorol", referencedColumnName = "codigorol"))
    private Set<Roles> roles =  new HashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "FAVORITOS_LIBPER",
            joinColumns = @JoinColumn(name = "codigous", referencedColumnName = "codigous"),
            inverseJoinColumns = @JoinColumn(name = "codigolib", referencedColumnName = "codigolib"))
    private Set<Libros> libros = new HashSet<>();

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

    @Override
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

    @Override
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

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Libros> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libros> libros) {
        this.libros = libros;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Roles rol : this.roles) {
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(rol.getNombre());
            authorities.add(sga);
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
