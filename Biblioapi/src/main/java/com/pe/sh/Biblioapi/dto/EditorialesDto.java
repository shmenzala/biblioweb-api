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
public class EditorialesDto {
    
    private String codigoedi;
    
    @NotEmpty(message = "El campo nombre no debe estar vacío")
    private String nombre;

    public EditorialesDto() {
    }

    public EditorialesDto(String codigoedi, String nombre) {
        this.codigoedi = codigoedi;
        this.nombre = nombre;
    }

    public String getCodigoedi() {
        return codigoedi;
    }

    public void setCodigoedi(String codigoedi) {
        this.codigoedi = codigoedi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
