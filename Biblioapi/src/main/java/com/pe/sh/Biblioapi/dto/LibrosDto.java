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
public class LibrosDto {
    
    private String codigolib;
    private String nombre;
    private String edicion;
    private String lugar_publicacion;
    private String fech_publicacion;
    private String paginas;
    private EditorialesDto codigoedi;
    private Set<AutoresDto> autores;
    private Set<GenerosDto> generos;

    public LibrosDto() {
    }

    public LibrosDto(String codigolib, String nombre, String edicion, String lugar_publicacion, String fech_publicacion, String paginas, EditorialesDto codigoedi, Set<AutoresDto> autores, Set<GenerosDto> generos) {
        this.codigolib = codigolib;
        this.nombre = nombre;
        this.edicion = edicion;
        this.lugar_publicacion = lugar_publicacion;
        this.fech_publicacion = fech_publicacion;
        this.paginas = paginas;
        this.codigoedi = codigoedi;
        this.autores = autores;
        this.generos = generos;
    }

    public String getCodigolib() {
        return codigolib;
    }

    public void setCodigolib(String codigolib) {
        this.codigolib = codigolib;
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

    public EditorialesDto getCodigoedi() {
        return codigoedi;
    }

    public void setCodigoedi(EditorialesDto codigoedi) {
        this.codigoedi = codigoedi;
    }

    public Set<AutoresDto> getAutores() {
        return autores;
    }

    public void setAutores(Set<AutoresDto> autores) {
        this.autores = autores;
    }

    public Set<GenerosDto> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<GenerosDto> generos) {
        this.generos = generos;
    }

    
    
    
    
}
