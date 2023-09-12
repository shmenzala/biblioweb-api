/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author shmen
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    
    private String nombre_recurso;
    private String nombre_campo;
    private String valor_campo;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String nombre_recurso, String nombre_campo, String valor_campo) {
        super(String.format("%s No encontrado con: %s = %s", nombre_recurso, nombre_campo, valor_campo));
        this.nombre_recurso = nombre_recurso;
        this.nombre_campo = nombre_campo;
        this.valor_campo = valor_campo;
    }

    public String getNombre_recurso() {
        return nombre_recurso;
    }

    public void setNombre_recurso(String nombre_recurso) {
        this.nombre_recurso = nombre_recurso;
    }

    public String getNombre_campo() {
        return nombre_campo;
    }

    public void setNombre_campo(String nombre_campo) {
        this.nombre_campo = nombre_campo;
    }

    public String getValor_campo() {
        return valor_campo;
    }

    public void setValor_campo(String valor_campo) {
        this.valor_campo = valor_campo;
    }

}
