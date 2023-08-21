/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.dto;

/**
 *
 * @author shmen
 */
public class AutoresDto {
    
    private String codigoaut;
    private String nombres;
    private String apellidos;
    private String fech_nacimiento;
    private String fotografia;
    private String extra_info;

    public AutoresDto() {
    }

    public AutoresDto(String codigoaut, String nombres, String apellidos, String fech_nacimiento, String fotografia, String extra_info) {
        this.codigoaut = codigoaut;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fech_nacimiento = fech_nacimiento;
        this.fotografia = fotografia;
        this.extra_info = extra_info;
    }

    public String getCodigoaut() {
        return codigoaut;
    }

    public void setCodigoaut(String codigoaut) {
        this.codigoaut = codigoaut;
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
    
    
    
}
