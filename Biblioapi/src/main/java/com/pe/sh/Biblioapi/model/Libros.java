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
@Table(name = "LIBROS")
public class Libros implements Serializable{
    
    @Id
    @Column(name = "codigolib")
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
    
    @Column(name = "codigoedi")
    private Editoriales editorial;

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
    
    
    
}
