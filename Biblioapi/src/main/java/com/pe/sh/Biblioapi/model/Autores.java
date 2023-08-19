/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author shmen
 */
@Entity
@Table(name = "AUTORES")
public class Autores implements Serializable{
    
    @Id
    @Column(name = "codigoaut")
    private String id;
    
    @Column(name = "nombres")
    private String nombres;
    
    @Column(name = "apellidos")
    private String apellidos;
    
    @Column(name = "fech_nacimiento")
    private String fech_nacimiento;
    
    @Column(name = "fotografia")
    private String fotografia;
    
    @Column(name = "extra_info")
    private String extra_info;
    
    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private Set<Libros> libros = new HashSet<>();

    public Autores() {
    }

    public Autores(String id, String nombres, String apellidos, String fech_nacimiento, String fotografia, String extra_info) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fech_nacimiento = fech_nacimiento;
        this.fotografia = fotografia;
        this.extra_info = extra_info;
    }

    public String getCodigoaut() {
        return id;
    }

    public void setCodigoaut(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFech_nacimiento() {
        return fech_nacimiento;
    }

    public void setFech_nacimiento(String fech_nacimiento) {
        this.fech_nacimiento = fech_nacimiento;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getExtra_info() {
        return extra_info;
    }

    public void setExtra_info(String extra_info) {
        this.extra_info = extra_info;
    }

    public Set<Libros> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libros> libros) {
        this.libros = libros;
    }
    
}
