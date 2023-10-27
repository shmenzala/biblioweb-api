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
public class RolesDto {
    
    private String codigorol;
    
    @NotEmpty(message = "El campo nombre no debe estar vacío")
    private String nombre;

    public RolesDto() {
    }

    public RolesDto(String codigorol, String nombre) {
        this.codigorol = codigorol;
        this.nombre = nombre;
    }

    public String getCodigorol() {
        return codigorol;
    }

    public void setCodigorol(String codigorol) {
        this.codigorol = codigorol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
