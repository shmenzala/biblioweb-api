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
@Table(name = "EDITORIALES")
public class Editoriales implements Serializable{
    
    @Id
    @Column(name = "codigoedi")
    private String id;
    
    @Column(name = "nombre")
    private String nombre;

    public Editoriales() {
    }

    public Editoriales(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getCodigoedi() {
        return id;
    }

    public void setCodigoedi(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
