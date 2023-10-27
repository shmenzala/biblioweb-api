/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 *
 * @author shmen
 */
public class Personas_perfilDto {
    
    private String codigope;
    
    @NotEmpty(message = "El campo nombres no debe estar vacío")
    private String nombres;
    
    @NotEmpty(message = "El campo apellidos no debe estar vacío")
    private String apellidos;
    
    @NotEmpty(message = "El campo género no debe estar vacío")
    private String genero;
    
    @NotEmpty(message = "El campo fech_nacimiento no debe estar vacío")
    private String fech_nacimiento;
    
    private String fotografia;

    public Personas_perfilDto() {
    }

    public Personas_perfilDto(String codigope, String nombres, String apellidos, String genero, String fech_nacimiento, String fotografia) {
        this.codigope = codigope;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.fech_nacimiento = fech_nacimiento;
        this.fotografia = fotografia;
    }

    public String getCodigope() {
        return codigope;
    }

    public void setCodigope(String codigope) {
        this.codigope = codigope;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
    
    
    
}
