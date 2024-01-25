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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "EDITORIALES")
public class Editoriales implements Serializable{
    
    @Id
    @Column(name = "codigoedi")
    @GeneratedValue(generator = "inc_seqEdi")
    @GenericGenerator(name = "inc_seqEdi", type = StringKeyGenerator.class,
            parameters = {@Parameter(name = "sqcName", value = "EDITORIALES_SEQ"),
                          @Parameter(name = "identificator_id", value = "ED")})
    private String id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @JsonBackReference
    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Libros> libros = new HashSet<>();

    public Editoriales() {
    }

    public Editoriales(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getCodigoedi() {
        return id;
    }

    public void setCodigoedi(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Libros> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libros> libros) {
        this.libros = libros;
    }
    
}
