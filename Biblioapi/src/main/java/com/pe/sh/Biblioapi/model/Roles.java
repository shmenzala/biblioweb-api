/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.model;

import com.pe.sh.Biblioapi.configuration.StringKeyGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author shmen
 */
@Entity
@Table(name = "ROLES")
public class Roles implements Serializable{
    
    @Id
    @Column(name = "codigorol")
    @GeneratedValue(generator = "inc_seqRol")
    @GenericGenerator(name = "inc_seqRol", type = StringKeyGenerator.class,
            parameters = {@Parameter(name = "sqcName", value = "ROLES_SEQ"),
                          @Parameter(name = "identificator_id", value = "RL")})
    private String id;
    
    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Usuarios> usuarios = new HashSet<>();

    public Roles() {
    }

    public Roles(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getCodigorol() {
        return id;
    }

    public void setCodigorol(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

}
