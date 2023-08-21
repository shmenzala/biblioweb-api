/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.dto;

/**
 *
 * @author shmen
 */
public class GenerosDto {
    
    private String codigogen;
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
