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
import jakarta.persistence.ManyToOne;
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
@Table(name = "LIBROS")
public class Libros implements Serializable{
    
    @Id
    @Column(name = "codigolib")
    @GeneratedValue(generator = "inc_seqLib")
    @GenericGenerator(name = "inc_seqLib", type = StringKeyGenerator.class,
            parameters = {@Parameter(name = "sqcName", value = "LIBROS_SEQ"),
                          @Parameter(name = "identificator_id", value = "LI")})
    private String id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "edicion")
    private String edicion;
    
    @Column(name = "lugar_publicacion")
    private String lugar_publicacion;
    
    @Column(name = "fech_publicacion")
    private String fech_publicacion;
    
    @Column(name = "paginas")
    private String paginas;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigoedi", nullable = false)
    private Editoriales editorial;
    
    @ManyToMany(mappedBy = "libros", fetch = FetchType.LAZY)
    private Set<Usuarios> usuarios = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "LIBROS_AUTORES",
            joinColumns = @JoinColumn(name = "codigolib", referencedColumnName = "codigolib"),
            inverseJoinColumns = @JoinColumn(name = "codigoaut", referencedColumnName = "codigoaut"))
    private Set<Autores> autores = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "LIBROS_GENEROS",
            joinColumns = @JoinColumn(name = "codigolib", referencedColumnName = "codigolib"),
            inverseJoinColumns = @JoinColumn(name = "codigogen", referencedColumnName = "codigogen"))
    private Set<Generos> generos = new HashSet<>();

    public Libros() {
    }

    public Libros(String id, String nombre, String edicion, String lugar_publicacion, String fech_publicacion, String paginas, Editoriales editorial) {
        this.id = id;
        this.nombre = nombre;
        this.edicion = edicion;
        this.lugar_publicacion = lugar_publicacion;
        this.fech_publicacion = fech_publicacion;
        this.paginas = paginas;
        this.editorial = editorial;
    }

    public String getCodigolib() {
        return id;
    }

    public void setCodigolib(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getLugar_publicacion() {
        return lugar_publicacion;
    }

    public void setLugar_publicacion(String lugar_publicacion) {
        this.lugar_publicacion = lugar_publicacion;
    }

    public String getFech_publicacion() {
        return fech_publicacion;
    }

    public void setFech_publicacion(String fech_publicacion) {
        this.fech_publicacion = fech_publicacion;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public Editoriales getEditorial() {
        return editorial;
    }

    public void setEditorial(Editoriales editorial) {
        this.editorial = editorial;
    }

    public Set<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public Set<Autores> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autores> autores) {
        this.autores = autores;
    }

    public Set<Generos> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<Generos> generos) {
        this.generos = generos;
    }
    
    
    
}
