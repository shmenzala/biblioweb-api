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
public class GenerosDto {
    
    private String codigogen;
    
    @NotEmpty(message = "El campo nombre no debe estar vacío")
    private String nombre;

    public GenerosDto() {
    }

    public GenerosDto(String codigogen, String nombre) {
        this.codigogen = codigogen;
        this.nombre = nombre;
    }

    public String getCodigogen() {
        return codigogen;
    }

    public void setCodigogen(String codigogen) {
        this.codigogen = codigogen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
